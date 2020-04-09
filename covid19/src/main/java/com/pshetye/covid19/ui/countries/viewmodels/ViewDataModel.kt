package com.pshetye.covid19.ui.countries.viewmodels

import androidx.annotation.StringDef
import androidx.annotation.StringRes

sealed class ViewDataModel(
    val uid: String,
    @SortedBy
    val sortedBy: String
)

class CountryViewDataModel(
    @SortedBy sortedBy: String,
    val countryName: String,
    val cases: Int,
    val recovered: Int,
    val critical: Int,
    val deaths: Int
) : ViewDataModel(
    countryName + (cases + recovered + critical + deaths).toString(),
    sortedBy
)

class TotalViewDataModel(
    @SortedBy sortedBy: String,
    @StringRes val title: Int,
    val countries: Int,
    val cases: Long,
    val recovered: Long,
    val critical: Long,
    val deaths: Long
) : ViewDataModel(
    (cases + recovered + critical + deaths).toString(),
    sortedBy
)

class ErrorViewDataModel(
    val errorMessage: String
) : ViewDataModel(errorMessage, SORT_OPTION_DEATHS)

const val ERROR_VIEW_TYPE = 0
const val TOTAL_VIEW_TYPE = 100
const val COUNTRY_VIEW_TYPE = 101

@[
    Retention(AnnotationRetention.SOURCE)
    StringDef(
        SORT_OPTION_CASES,
        SORT_OPTION_RECOVERIES,
        SORT_OPTION_CRITICAL,
        SORT_OPTION_DEATHS
    )
]
annotation class SortedBy

const val SORT_OPTION_CASES = "CASES"
const val SORT_OPTION_RECOVERIES = "RECOVERIES"
const val SORT_OPTION_CRITICAL = "CRITICAL"
const val SORT_OPTION_DEATHS = "DEATHS"