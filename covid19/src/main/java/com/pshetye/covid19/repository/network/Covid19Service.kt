package com.pshetye.covid19.repository.network

import com.pshetye.covid19.models.Countries
import io.reactivex.Single
import retrofit2.http.GET

interface Covid19Service {
    @GET("countries")
    fun getCountries(): Single<Countries>
}