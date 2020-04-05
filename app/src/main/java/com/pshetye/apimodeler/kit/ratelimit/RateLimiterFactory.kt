package com.pshetye.apimodeler.kit.ratelimit

import dagger.Reusable
import java.util.*
import javax.inject.Inject

@Reusable
class RateLimiterFactory @Inject constructor(
    private val calendar: Calendar
) {
    fun create(
        limitTimeInMilliseconds: Long,
        timeOfRequestInMilliseconds: Long = -1
    ): RateLimiter = RateLimiter(
        limitTimeInMilliseconds,
        timeOfRequestInMilliseconds,
        calendar
    )
}