package com.anooplab.ibwrapper.model

/**
 * Kotlin idiomatic wrapper for IB API's ExecutionFilter model.
 */
data class ExecutionFilterKT(
    val clientId: Int? = null,
    val acctCode: String? = null,
    val time: String? = null,
    val symbol: String? = null,
    val secType: String? = null,
    val exchange: String? = null,
    val side: String? = null
)
