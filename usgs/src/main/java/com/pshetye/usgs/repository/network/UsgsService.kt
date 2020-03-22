package com.pshetye.usgs.repository.network

import com.pshetye.usgs.models.FeatureCollection
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UsgsService {
    @GET("earthquakes/feed/v1.0/summary/{earthquake-magnitude-time-identifier}")
    fun getEarthquakes(
        @Path(
            value = "earthquake-magnitude-time-identifier",
            encoded = true
        ) magnitudeTimeIdentifier: String
    ): Single<FeatureCollection>
}