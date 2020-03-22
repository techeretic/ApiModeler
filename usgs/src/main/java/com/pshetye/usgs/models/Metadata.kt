package com.pshetye.usgs.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metadata(
    val api: String?,
    val count: Int?,
    val generated: Long?,
    val status: Int?,
    val title: String?,
    val url: String?
)