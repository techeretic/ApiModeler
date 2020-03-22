package com.pshetye.apimodeler.di.components

import com.pshetye.apimodeler.MainActivity
import com.pshetye.apimodeler.di.modules.DeserializationModule
import com.pshetye.apimodeler.di.modules.NetworkModule
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@[Singleton Component(
    modules = [
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

    fun getFragmentComponentFactory(): FragmentComponent.Factory

    fun inject(mainActivity: MainActivity)
}