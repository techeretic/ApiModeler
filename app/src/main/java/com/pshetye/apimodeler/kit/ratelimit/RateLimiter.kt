package com.pshetye.apimodeler.kit.ratelimit

import java.util.Calendar

class RateLimiter(
    private val limitTimeInMilliseconds: Long,
    var timeOfRequestInMilliseconds: Long,
    private val calendar: Calendar
) {
    fun shouldPerformRequest() =
        (limitTimeInMilliseconds + timeOfRequestInMilliseconds) < calendar.timeInMillis
}