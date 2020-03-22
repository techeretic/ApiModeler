package com.pshetye.apimodeler.ui.home.models

import androidx.annotation.IdRes

data class SupportedModule(
    val moduleName: String,
    @IdRes val navigationRes: Int
)