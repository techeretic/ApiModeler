package com.pshetye.cacovid19.ui.counties.viewmodels

import androidx.annotation.StringRes

sealed class CountiesViewDataModel(val uid: String)

class CountyViewDataModel(
    val countyName: String,
    val cases: Long,
    val critical: Long,
    val deaths: Long
): CountiesViewDataModel(
    countyName + (cases + critical + deaths).toString()
)

class TotalsViewDataModel(
    val totalCounties: Int,
    @StringRes val headerStringRes: Int,
    val cases: Long,
    val critical: Long,
    val deaths: Long,
    val lastUpdatedDate: String
): CountiesViewDataModel(
    TotalsViewDataModel::class.simpleName
            + (totalCounties + cases + critical + deaths).toString()
            + lastUpdatedDate
)

class ErrorViewDataModel(
    val errorMessage: String
) : CountiesViewDataModel(errorMessage)

const val ERROR_VIEW_TYPE = 0
const val TOTAL_VIEW_TYPE = 100
const val COUNTY_VIEW_TYPE = 101
