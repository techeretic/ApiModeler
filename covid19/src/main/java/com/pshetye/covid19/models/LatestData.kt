package com.pshetye.covid19.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatestData(
    val calculated: Calculated?,
    val confirmed: Int,
    val critical: Int,
    val deaths: Int,
    val recovered: Int
)