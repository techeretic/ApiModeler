package com.pshetye.covid19.ui.countries.viewmodels

import androidx.annotation.StringRes
import java.util.*

sealed class ViewDataModel(
    val uid: String = UUID.randomUUID().toString()
)

class CountryViewDataModel(
    val countryName: String,
    val cases: Int,
    val recovered: Int,
    val deaths: Int
) : ViewDataModel()

class TotalViewDataModel(
    @StringRes val title: Int,
    val countries: Int,
    val cases: Long,
    val recovered: Long,
    val deaths: Long
) : ViewDataModel()

const val TOTAL_VIEW_TYPE = 100
const val COUNTRY_VIEW_TYPE = 101