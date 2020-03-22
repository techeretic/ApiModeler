package com.pshetye.usgs.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeatureCollection(
    val bbox: List<Double>?,
    val features: List<Feature>?,
    val metadata: Metadata?,
    val type: String?
)