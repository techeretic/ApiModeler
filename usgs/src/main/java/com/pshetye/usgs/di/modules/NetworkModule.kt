package com.pshetye.usgs.di.modules

import com.pshetye.usgs.di.annotations.URL
import com.pshetye.usgs.di.scopes.USGS
import com.pshetye.usgs.repository.network.UsgsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
object NetworkModule {
    @[Provides URL]
    fun provideBaseUrl(): String = "https://earthquake.usgs.gov/"

    @[Provides USGS]
    fun provideRetrofit(
        @URL baseUrl: String,
        okHttpClientBuilder: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder
    ): Retrofit {
        return retrofitBuilder
            .baseUrl(baseUrl)
            .client(okHttpClientBuilder.build())
            .build()
    }

    @[Provides USGS]
    fun provideUsgsService(retrofit: Retrofit): UsgsService =
        retrofit.create(UsgsService::class.java)
}