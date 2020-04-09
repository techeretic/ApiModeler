package com.pshetye.cacovid19.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val fields: List<Field>,
    val records: List<Record>,
    val sql: String
)