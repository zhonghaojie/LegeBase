package com.lege.android.base.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.DisplayMetrics;

import com.lege.android.base.log.APPLog;

/**
 * Created by zhoushaoqing on 18-10-18.
 */

public class BitmapUtil {
    public static Bitmap blur(Context context, Bitmap bitmap, float radius) {
        // 创建输出图片
        Bitmap output = Bitmap.createBitmap(bitmap);
        // 构建一个RenderScript对象
        RenderScript rs = RenderScript.create(context);
        // 创建高斯模糊脚本
        ScriptIntrinsicBlur gaussianBlue = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        // 创建用于输入的脚本类型
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        // 创建用于输出的脚本类型
        Allocation allOut = Allocation.createFromBitmap(rs, output);
        // 设置模糊半径，范围0f<radius<=25f
        gaussianBlue.setRadius(radius);
        // 设置输入脚本类型
        gaussianBlue.setInput(allIn);
        // 执行高斯模糊算法，并将结果填入输出脚本类型中
        gaussianBlue.forEach(allOut);
        // 将输出内存编码为Bitmap，图片大小必须注意
        allOut.copyTo(output);
        // 关闭RenderScript对象，API>=23则使用rs.releaseAllContexts()
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            rs.releaseAllContexts();
        }else{
            rs.destroy();
        }
        return output;
    }
    public static Drawable getBackBlurDrawable(Context context, Drawable drawable,float radius) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();
        return new BitmapDrawable(context.getResources(),
                blur(context,bitmap,radius));
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap){
        if (bitmap!= null){
            APPLog.log("bitmapToDrawable=="+bitmap.getWidth()+"=="+bitmap.getHeight());
        }
        Drawable drawable = new BitmapDrawable(bitmap);
        return drawable;
    }

    public static Bitmap DrawableToBitmap(Drawable drawable){
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bm= bd.getBitmap();
        return bm;
    }

    public static Bitmap getScaleBitmap(String imagePath, int width,
                                           int height) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, width,
                height);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imagePath, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        //先根据宽度进行缩小
        while (width / inSampleSize > reqWidth) {
            inSampleSize++;
        }
        //然后根据高度进行缩小
        while (height / inSampleSize > reqHeight) {
            inSampleSize++;
        }
        return inSampleSize;
    }

    public static int getScreenWidth(Context context){
        DisplayMetrics dm =context.getResources().getDisplayMetrics();
        return dm.widthPixels;

    }
    public static int getScreenHeight(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getDensity(Context context){
       return  context.getResources().getDisplayMetrics().density;
    }
    // 缩放图片
    public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    public static Bitmap getRoundBitmap(Bitmap bitmap, int roundPx) {
        APPLog.log("bitmap.getWidth()=="+bitmap.getWidth()+"==bitmap.getHeight()=="+bitmap.getHeight());
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap getRoundRectBitmap(Bitmap bitmap, int radius) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        int bmWidth = bitmap.getWidth();
        int bmHeight = bitmap.getHeight();
        final RectF rectF = new RectF(0, 0, bmWidth, bmHeight);
        final Rect rect = new Rect(0, 0, bmWidth, bmHeight);
        Canvas canvas = new Canvas(bitmap);
        paint.setXfermode(null);
        canvas.drawRoundRect(rectF, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        return bitmap;
    }

    /**
     * 按长方形裁切图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap ImageCropWithRect(Bitmap bitmap,int x,int y,int width ,int height)
    {
        if (bitmap == null)
        {
            return null;
        }
        Bitmap bmp = Bitmap.createBitmap(bitmap, x, y,
                width, height);
        return bmp;
    }

    /**
     * 用矩阵放大缩小bitmap
     * @param bitmap
     * @param scale
     * @return
     */
    public static Bitmap bitMapScale(Bitmap bitmap,float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return resizeBmp;
    }


}
