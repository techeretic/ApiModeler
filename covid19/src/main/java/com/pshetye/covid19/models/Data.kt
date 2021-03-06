package com.pshetye.covid19.models

import com.pshetye.covid19.ui.countries.viewmodels.CountryViewDataModel
import com.pshetye.covid19.ui.countries.viewmodels.SortedBy
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Data(
    val code: String?,
    val coordinates: Coordinates?,
    val latest_data: LatestData?,
    val name: String?,
    val population: Long?,
    val today: Today?,
    val updated_at: Date?
)

fun Data.toViewDataModel(@SortedBy sortedBy: String): CountryViewDataModel? =
    latest_data?.let {
        CountryViewDataModel(
            sortedBy,
            updated_at!!,
            name!!,
            it.confirmed,
            it.recovered,
            it.critical,
            it.deaths
        )
    }