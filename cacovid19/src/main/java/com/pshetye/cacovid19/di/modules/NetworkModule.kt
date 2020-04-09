package com.pshetye.cacovid19.di.modules

import com.pshetye.apimodeler.di.qualifiers.Cached
import com.pshetye.cacovid19.di.annotations.URL
import com.pshetye.cacovid19.di.scopes.CaCovid19
import com.pshetye.cacovid19.repository.network.CaCovid19Service
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
object NetworkModule {
    @[Provides URL]
    fun provideBaseUrl(): String = "https://data.chhs.ca.gov/"

    @[Provides CaCovid19]
    fun provideRetrofit(
        @URL baseUrl: String,
        @Cached okHttpClientBuilder: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder
    ): Retrofit {
        return retrofitBuilder
            .baseUrl(baseUrl)
            .client(okHttpClientBuilder.build())
            .build()
    }

    @[Provides CaCovid19]
    fun provideCovid19Service(
        retrofit: Retrofit
    ): CaCovid19Service {
        return retrofit.create(CaCovid19Service::class.java)
    }
}