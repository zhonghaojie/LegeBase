package com.lege.android.base.retrofit

import android.util.Log
import android.webkit.WebSettings
import com.lege.android.base.BaseApp
import com.lege.android.base.wifi.WifiHelper
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Description:
 * Created by zhonghaojie on 2019-08-14.
 */
class BaseRetrofit {
    companion object {
        @JvmStatic
        val retrofit: Retrofit by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            createRetrofit()
        }

        /**
         * 设置头
         */
        private fun addHeaderInterceptor(): Interceptor {
            return Interceptor { chain ->
                val originalRequest = chain.request()
                val requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
                    .removeHeader("User-Agent")//移除旧的
                    .addHeader("User-Agent", WebSettings.getDefaultUserAgent(BaseApp.getAppContext()))
                    .method(originalRequest.method(), originalRequest.body())
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }

        @JvmStatic
        private fun createRetrofit(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val clientBuilder = OkHttpClient.Builder()
            val client = clientBuilder
                .connectTimeout(60L, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .eventListener(object:EventListener(){
                    override fun callStart(call: Call) {
                        super.callStart(call)
                        if(!WifiHelper.getInstance().isNetworkConnected){
                            GlobalRequestWatcher.networkUnavailable()
                            call.cancel()
                        }
                    }
                })
                .writeTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(Urls.BASE)
                //.baseUrl(Urls.TEST_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }
}

