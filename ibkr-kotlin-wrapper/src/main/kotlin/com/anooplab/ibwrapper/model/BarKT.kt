package com.anooplab.ibwrapper.model

/**
 * Kotlin idiomatic wrapper for IB API's Bar model.
 */
data class BarKT(
    val time: String?,
    val open: Double?,
    val high: Double?,
    val low: Double?,
    val close: Double?,
    val volume: Long?,
    val count: Int?,
    val wap: Double?
)
