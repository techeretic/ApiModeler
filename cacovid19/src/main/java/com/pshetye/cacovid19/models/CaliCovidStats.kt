package com.pshetye.cacovid19.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CaliCovidStats(
    val help: String,
    val result: Result,
    val success: Boolean
)