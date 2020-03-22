package com.pshetye.usgs.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Feature(
    val geometry: Geometry,
    val id: String,
    val properties: Properties,
    val type: String
)