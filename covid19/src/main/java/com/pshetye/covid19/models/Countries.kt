package com.pshetye.covid19.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Countries(
    val _cacheHit: Boolean,
    val data: List<Data>?
)