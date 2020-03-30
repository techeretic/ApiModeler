package com.pshetye.covid19.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Today(
    val confirmed: Int,
    val deaths: Int
)