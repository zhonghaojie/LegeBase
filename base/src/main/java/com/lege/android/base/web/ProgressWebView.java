package com.lege.android.base.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by zhoushaoqing on 19-3-20.
 */

public class ProgressWebView extends WebView {

    private WebViewProgressBar progressBar;
    private Context context;
    private Handler handler;
    private WebView mWebView;

    public WebViewProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(WebViewProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
       /* //实例化进度条
        progressBar = new WebViewProgressBar(context);
        //设置进度条的size
        progressBar.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //刚开始时候进度条不可见
        progressBar.setVisibility(GONE);
        //把进度条添加到webView里面
        addView(progressBar);*/
        //初始化handle
        handler = new Handler();
        mWebView = this;
        initSettings();
    }

    protected void initSettings() {
        // 初始化设置
        WebSettings mSettings = this.getSettings();
        mSettings.setJavaScriptEnabled(true);//开启javascript
        mSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        mSettings.setPluginState(WebSettings.PluginState.ON);
        mSettings.setDomStorageEnabled(true);//开启DOM
        mSettings.setNeedInitialFocus(true);
        mSettings.setDefaultTextEncodingName("utf-8");//设置字符编码
        //设置web页面
        mSettings.setAllowFileAccess(true);//设置支持文件流
        mSettings.setSupportZoom(true);// 支持缩放
        mSettings.setBuiltInZoomControls(true);// 支持缩放
        mSettings.setDisplayZoomControls(false);
        //region 适应屏幕
        mSettings.setUseWideViewPort(true);// 配合mSetInitialScale 调整到适合webview大小
        mSettings.setLoadWithOverviewMode(true);// 调整到适合webview大小
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //endregion
        mSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片
        mSettings.setBlockNetworkImage(true);
        mSettings.setAppCacheEnabled(false);//开启缓存机制
        setHorizontalScrollBarEnabled(false); // 水平不显示滚动条
        setWebViewClient(new MyWebClient());
        setWebChromeClient(new MyWebChromeClient());
        if (getContext() instanceof Activity) {
            mSetInitialScale((Activity) getContext());
        }

    }

    public void mSetInitialScale(Activity aty) {
        WindowManager wm = (WindowManager) aty.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        Log.i("网页缩放", "width  " + width);
        if (width > 650) {
            setInitialScale(190);
        } else if (width > 520) {
            setInitialScale(160);
        } else if (width > 450) {
            setInitialScale(140);
        } else if (width > 300) {
            setInitialScale(120);
        } else {
            setInitialScale(100);
        }
    }

    /**
     * 自定义WebChromeClient
     */
    public class MyWebChromeClient extends WebChromeClient {
        /**
         * 进度改变的回掉
         *
         * @param view        WebView
         * @param newProgress 新进度
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100 && progressBar != null) {
                progressBar.setProgress(100);
                handler.postDelayed(runnable, 200);//0.2秒后隐藏进度条
            } else if (progressBar != null && progressBar.getVisibility() == GONE) {
                progressBar.setVisibility(VISIBLE);
            }
            //设置初始进度10，这样会显得效果真一点，总不能从1开始吧
            if (newProgress < 10) {
                newProgress = 10;
            }
            //不断更新进度
            if (progressBar != null) {
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    public class MyWebClient extends WebViewClient {
        /**
         * 加载过程中 拦截加载的地址url
         *
         * @param view
         * @param url  被拦截的url
         * @return
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            mWebView.loadUrl(url);
//            return true;
            if (url == null) {
                return false;
            }

            try {
                if (url.startsWith("weixin://") //微信
                        || url.startsWith("alipays://") //支付宝
                        || url.startsWith("mailto://") //邮件
                        || url.startsWith("tel://")//电话
                        || url.startsWith("dianping://")//大众点评
                        || url.startsWith("baidu")//百度
                        || url.startsWith("bilibili://")//哔哩哔哩
                        || url.startsWith("douban://")//豆瓣
                        || url.startsWith("pinduoduo://")//拼多多
                        || url.startsWith("snssdk1128")//抖音
                        || url.startsWith("tmall://")//淘宝
                        || url.startsWith("tbopen://")//淘宝
                    //其他自定义的scheme
                ) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(intent);
                    return true;
                }
            } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
            }

            //处理http和https开头的url
            mWebView.loadUrl(url);
            return true;
        }

        /**
         * 页面加载过程中，加载资源回调的方法
         *
         * @param view
         * @param url
         */
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        /**
         * 页面加载完成回调的方法
         *
         * @param view
         * @param url
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // 关闭图片加载阻塞
            view.loadUrl("javascript: " +
                    "document.οncοntextmenu = function () { return false;};" +
                    "document.onselectstart = function () { return false;};");
            view.getSettings().setBlockNetworkImage(false);
        }

        /**
         * 页面开始加载调用的方法
         *
         * @param view
         * @param url
         * @param favicon
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            super.onScaleChanged(view, oldScale, newScale);
            ProgressWebView.this.requestFocus();
            ProgressWebView.this.requestFocusFromTouch();
        }
    }

    /**
     * 刷新界面（此处为加载完成后进度消失）
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
        }
    };
}