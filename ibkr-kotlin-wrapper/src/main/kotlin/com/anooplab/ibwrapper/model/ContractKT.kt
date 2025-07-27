package com.anooplab.ibwrapper.model

import java.math.BigDecimal

/**
 * Kotlin idiomatic wrapper for IB API's Contract model.
 */
data class ContractKT(
    val conId: Int? = null,
    val symbol: String? = null,
    val secType: String? = null,
    val lastTradeDateOrContractMonth: String? = null,
    val strike: Double? = null,
    val right: String? = null,
    val multiplier: String? = null,
    val exchange: String? = null,
    val primaryExch: String? = null,
    val currency: String? = null,
    val localSymbol: String? = null,
    val tradingClass: String? = null,
    val includeExpired: Boolean? = null,
    val secIdType: String? = null,
    val secId: String? = null
)
