package com.lege.android.base.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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

        @JvmStatic
        private fun createRetrofit(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val clientBuilder = OkHttpClient.Builder()
            val client = clientBuilder
                    .connectTimeout(60L, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
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

