package com.pshetye.apimodeler.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
object DeserializationModule {
    @[Provides Singleton]
    fun provideMoshi(
        kotlinJsonAdapterFactory: KotlinJsonAdapterFactory
    ): Moshi {
        return Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }

    @Provides
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory =
        KotlinJsonAdapterFactory()
}