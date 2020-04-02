package com.pshetye.apimodeler.ui.home.models

import androidx.annotation.IdRes
import androidx.annotation.StringRes

data class SupportedModule(
    @StringRes val headerTitle: Int,
    @IdRes val navigationRes: Int
)