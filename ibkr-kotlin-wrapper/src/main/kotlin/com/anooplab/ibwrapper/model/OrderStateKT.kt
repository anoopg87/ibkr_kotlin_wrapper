package com.anooplab.ibwrapper.model

/**
 * Kotlin idiomatic wrapper for IB API's OrderState model.
 */
data class OrderStateKT(
    val status: OrderStatusKT?,
    val initMarginBefore: String?,
    val maintMarginBefore: String?,
    val equityWithLoanBefore: String?,
    val initMarginChange: String?,
    val maintMarginChange: String?,
    val equityWithLoanChange: String?,
    val initMarginAfter: String?,
    val maintMarginAfter: String?,
    val equityWithLoanAfter: String?,
    val commission: Double?,
    val minCommission: Double?,
    val maxCommission: Double?,
    val commissionCurrency: String?,
    val warningText: String?
)
