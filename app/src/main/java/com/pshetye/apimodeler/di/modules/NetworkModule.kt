package com.pshetye.apimodeler.di.modules

import android.content.Context
import com.pshetye.apimodeler.di.qualifiers.Cached
import com.pshetye.apimodeler.di.qualifiers.Default
import com.pshetye.apimodeler.networking.cache.CacheInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
object NetworkModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @[Provides Singleton Default]
    fun provideBaseOkHttpClientBuilder(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
    }

    @Provides
    fun provideCache(
        applicationContext: Context
    ): Cache {
        val httpCacheDirectory = File(applicationContext.cacheDir, "http-cache")
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(httpCacheDirectory, cacheSize.toLong())
    }

    @[Provides Singleton Cached]
    fun provideCachedOkHttpClientBuilder(
        @Default okhttpClientBuilder: OkHttpClient.Builder,
        cacheInterceptor: CacheInterceptor,
        cache: Cache
    ): OkHttpClient.Builder {
        return okhttpClientBuilder
            .addInterceptor(cacheInterceptor)
            .cache(cache)
    }

    @[Provides Singleton]
    fun provideBaseRetrofitBuilder(
        moshi: Moshi
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }
}