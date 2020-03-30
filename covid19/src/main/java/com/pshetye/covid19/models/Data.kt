package com.pshetye.covid19.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val code: String?,
    val coordinates: Coordinates?,
    val latest_data: LatestData?,
    val name: String?,
    val population: Long?,
    val today: Today?,
    val updated_at: String?
)