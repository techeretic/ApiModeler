package com.pshetye.covid19.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Calculated(
    val cases_per_million_population: Double?,
    val death_rate: Double?,
    val recovered_vs_death_ratio: Double?,
    val recovery_rate: Double?
)