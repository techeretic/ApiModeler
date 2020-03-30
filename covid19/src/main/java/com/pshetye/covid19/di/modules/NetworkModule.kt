package com.pshetye.covid19.di.modules

import com.pshetye.covid19.di.annotations.URL
import com.pshetye.covid19.di.scopes.Covid19
import com.pshetye.covid19.repository.network.Covid19Service
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
object NetworkModule {
    @[Provides URL]
    fun provideBaseUrl(): String = "https://corona-api.com/"

    @[Provides Covid19]
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

    @[Provides Covid19]
    fun provideCovid19Service(
        retrofit: Retrofit
    ): Covid19Service {
        return retrofit.create(Covid19Service::class.java)
    }
}