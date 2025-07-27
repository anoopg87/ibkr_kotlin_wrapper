package com.anooplab.ibwrapper.model

/**
 * Kotlin idiomatic wrapper for IB API's DepthMktDataDescription model.
 */
data class DepthMktDataDescriptionKT(
    val exchange: String?,
    val secType: String?,
    val listingExch: String?,
    val serviceDataType: String?,
    val aggGroup: Int?
)
