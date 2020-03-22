package com.pshetye.usgs.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Properties(
    val alert: String?,
    val cdi: Double?,
    val code: String?,
    val detail: String?,
    val dmin: Double?,
    val felt: Int?,
    val gap: Double?,
    val ids: String?,
    val mag: Double?,
    val magType: String?,
    val mmi: Double?,
    val net: String?,
    val nst: Int?,
    val place: String?,
    val rms: Double?,
    val sig: Int?,
    val sources: String?,
    val status: String?,
    val time: Long?,
    val title: String?,
    val tsunami: Int?,
    val type: String?,
    val types: String?,
    val tz: Int?,
    val updated: Long?,
    val url: String?
)