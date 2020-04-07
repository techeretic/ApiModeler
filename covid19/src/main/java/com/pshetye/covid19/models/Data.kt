package com.pshetye.covid19.models

import com.pshetye.covid19.ui.countries.viewmodels.CountriesViewModel
import com.pshetye.covid19.ui.countries.viewmodels.CountryViewDataModel
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

fun Data.toViewDataModel(): CountryViewDataModel? =
    latest_data?.let {
        CountryViewDataModel(
            name!!,
            it.confirmed,
            it.recovered,
            it.critical,
            it.deaths
        )
    }