package com.anooplab.ibwrapper.model

/**
 * Kotlin idiomatic wrapper for IB API's ContractDescription model.
 */
data class ContractDescriptionKT(
    val contract: ContractKT?,
    val derivativeSecTypes: List<String>?
)
