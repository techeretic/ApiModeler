package com.pshetye.cacovid19.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Field(
    val id: String,
    val type: String
)