package com.pshetye.cacovid19.models

import com.pshetye.cacovid19.ui.counties.viewmodels.CountyViewDataModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class Record(
    @Json(name = "COVID-19 Positive Patients")
    val positivePatients: Long?,
    @Json(name = "ICU COVID-19 Positive Patients")
    val positivePatientsInIcu: Long?,
    @Json(name = "ICU COVID-19 Suspected Patients")
    val suspectedPatientsInIcu: Long?,
    @Json(name = "Most Recent Date")
    val mostRecentDate: String?,
    @Json(name = "Suspected COVID-19 Positive Patients")
    val suspectedPatients: Long?,
    @Json(name = "Total Count Confirmed")
    val totalConfirmedCases: Long?,
    @Json(name = "Total Count Deaths")
    val totalDeaths: Long?,
    @Json(name = "County Name")
    val countyName: String?,
    @Json(name = "_id")
    val id: Int?
)

fun Record.toViewDataModel(): CountyViewDataModel =
    CountyViewDataModel(
        countyName = countyName ?: "",
        cases = totalConfirmedCases ?: 0,
        critical = (positivePatientsInIcu ?: 0) + (suspectedPatientsInIcu ?: 0),
        deaths = totalDeaths ?: 0
    )