package com.skfo763.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.DateTypeAdapter
import com.skfo763.remote.util.StethoUtils
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.TimeUnit

class RetroBuilder {

    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateTypeAdapter())
        .setLenient()
        .create()

    fun getRetrofit(isSSL: Boolean = false) = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(HeaderInterceptor())
            .addNetworkInterceptor(StethoInterceptor())
            .apply {
                if(BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }.build()
        ).build()

    class HeaderInterceptor: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val interceptedRequest = chain.request().newBuilder()
                .header("User-Agent", "policy-app-header-test")
                .build()
            return chain.proceed(interceptedRequest)
        }
    }

    class NullOnEmptyConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
            val delegate = retrofit!!.nextResponseBodyConverter<Any>(this, type!!, annotations!!)
            return Converter<ResponseBody, Any> {
                if (it.contentLength() == 0L) return@Converter okhttp3.internal.EMPTY_RESPONSE
                delegate.convert(it)
            }
        }
    }
}