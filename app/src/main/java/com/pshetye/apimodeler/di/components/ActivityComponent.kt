package com.pshetye.apimodeler.di.components

import com.pshetye.apimodeler.MainActivity
import com.pshetye.apimodeler.di.modules.AppModule
import com.pshetye.apimodeler.di.modules.DeserializationModule
import com.pshetye.apimodeler.di.modules.NetworkModule
import com.pshetye.apimodeler.kit.ratelimit.RateLimiterFactory
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.Calendar
import javax.inject.Singleton

@[Singleton Component(
    modules = [
        AppModule::class,
        DeserializationModule::class,
        NetworkModule::class
    ]
)]
interface ActivityComponent {
    @Component.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun getBaseOkHttpClientBuilder(): OkHttpClient.Builder

    fun getBaseRetrofitBuilder(): Retrofit.Builder

    fun getRateLimiterFactory(): RateLimiterFactory

    fun getCalendar(): Calendar

    fun getFragmentComponentFactory(): FragmentComponent.Factory

    fun inject(mainActivity: MainActivity)
}