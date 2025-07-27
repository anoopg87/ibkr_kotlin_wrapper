package com.anooplab.ibwrapper.model

/**
 * Kotlin idiomatic wrapper for IB API's Execution model.
 */
data class ExecutionKT(
    val execId: String?,
    val time: String?,
    val acctNumber: String?,
    val exchange: String?,
    val side: String?,
    val shares: Double?,
    val price: Double?,
    val permId: Int?,
    val clientId: Int?,
    val orderId: Int?,
    val liquidation: Int?,
    val cumQty: Double?,
    val avgPrice: Double?,
    val orderRef: String?,
    val evRule: String?,
    val evMultiplier: Double?
)
