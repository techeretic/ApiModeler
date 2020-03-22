package com.pshetye.apimodeler.di.interfaces

import com.pshetye.apimodeler.di.components.ActivityComponent

interface ProvideActivityComponent {
    fun getActivityComponent(): ActivityComponent
}