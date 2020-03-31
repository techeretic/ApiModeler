package com.pshetye.covid19.ui.countries.viewmodels

data class CountryViewDataModel(
    val countryName: String,
    val cases: Int,
    val recovered: Int,
    val deaths: Int
)