package com.anooplab.ibwrapper.model

/**
 * Kotlin idiomatic wrapper for IB API's CommissionReport model.
 */
data class CommissionReportKT(
    val execId: String?,
    val commission: Double?,
    val currency: String?,
    val realizedPNL: Double?,
    val yieldValue: Double?,
    val yieldRedemptionDate: Int?
)
