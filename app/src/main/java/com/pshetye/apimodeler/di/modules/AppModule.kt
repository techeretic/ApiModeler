package com.pshetye.apimodeler.di.modules

import dagger.Module
import dagger.Provides
import java.util.Calendar

@Module
object AppModule {
    @Provides
    fun provideCalendar(): Calendar = Calendar.getInstance()
}