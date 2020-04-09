package com.pshetye.cacovid19.repository.network

import com.pshetye.cacovid19.models.CaliCovidStats
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CaCovid19Service {
    @[GET("api/3/action/datastore_search_sql?")]
    fun getCaliforniaCasesViaSql(
        @Query("sql") completeQuery: String
    ): Single<CaliCovidStats>
}