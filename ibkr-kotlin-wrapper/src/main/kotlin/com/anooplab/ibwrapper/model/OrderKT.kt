package com.anooplab.ibwrapper.model

/**
 * Kotlin idiomatic wrapper for IB API's Order model.
 */
data class OrderKT(
    val orderId: Int? = null,
    val action: String? = null,
    val totalQuantity: Double? = null,
    val orderType: String? = null,
    val lmtPrice: Double? = null,
    val auxPrice: Double? = null,
    val tif: String? = null,
    val ocaGroup: String? = null,
    val account: String? = null,
    val openClose: String? = null,
    val origin: Int? = null,
    val orderRef: String? = null,
    val transmit: Boolean? = null,
    val parentId: Int? = null,
    val blockOrder: Boolean? = null,
    val sweepToFill: Boolean? = null,
    val displaySize: Int? = null,
    val triggerMethod: Int? = null,
    val outsideRth: Boolean? = null,
    val hidden: Boolean? = null,
    val clientId: Int? = null,
    val discretionaryAmt: Double? = null,
    val goodAfterTime: String? = null,
    val goodTillDate: String? = null,
    val faGroup: String? = null,
    val faMethod: String? = null,
    val faPercentage: String? = null,
    val faProfile: String? = null,
    val modelCode: String? = null
    // Add more fields as needed
)
