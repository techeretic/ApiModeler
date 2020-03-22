package com.pshetye.usgs.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geometry(
    val coordinates: List<Double>,
    val type: String
)