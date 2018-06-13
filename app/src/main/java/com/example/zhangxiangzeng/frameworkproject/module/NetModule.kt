package com.example.zhangxiangzeng.frameworkproject.module

import android.app.Application
import com.example.zhangxiangzeng.frameworkproject.rest.ApiService
import com.example.zhangxiangzeng.frameworkproject.rest.RestService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * <pre>
 *     e-mail : majingze@shuangke.net.cn
 *     time   : 2018/6/11
 *     desc   :
 *     version: 1.0
 *     Copyright: Copyright（c）2017
 *     Company:爽客智能设备有限公司
 * </pre>
 * @author majingze
 */
@Module
class NetModule(var baseUrl: String) {

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(application.cacheDir, cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient = OkHttpClient()
            .newBuilder()
            .cache(cache)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor()).build()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()

//    @Provides
//    fun provideApiService() = provideRetrofit().create(ApiService::class.java)

    @Provides
    fun provideRestService() = RestService()

    companion object {

        private val CONNECT_TIME_OUT = 15L

        private val WRITE_TIME_OUT = 15L

        private val READ_TIME_OUT = 15L
        /**
         * 本地测试环境地址
         */
        val LOCAL_SERVER_ADDRESS = "http://192.168.8.148:7204"
    }
}