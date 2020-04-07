package com.pshetye.covid19.ui.countries.viewmodels

import androidx.annotation.StringRes
import java.util.*

sealed class ViewDataModel(
    val uid: String
)

class CountryViewDataModel(
    val countryName: String,
    val cases: Int,
    val recovered: Int,
    val critical: Int,
    val deaths: Int
) : ViewDataModel(
    countryName + (cases + recovered + critical + deaths).toString()
)

class TotalViewDataModel(
    @StringRes val title: Int,
    val countries: Int,
    val cases: Long,
    val recovered: Long,
    val critical: Long,
    val deaths: Long
) : ViewDataModel(
    (cases + recovered + critical + deaths).toString()
)

const val TOTAL_VIEW_TYPE = 100
const val COUNTRY_VIEW_TYPE = 101
