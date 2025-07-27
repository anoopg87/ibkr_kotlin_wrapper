package com.anooplab.ibwrapper.model

import com.ib.client.DepthMktDataDescription

fun DepthMktDataDescription.toKT(): DepthMktDataDescriptionKT = DepthMktDataDescriptionKT(
    exchange = exchange(),
    secType = secType(),
    listingExch = listingExch(),
    serviceDataType = serviceDataType(),
    aggGroup = aggGroup()
)

fun DepthMktDataDescriptionKT.toIbApi(): DepthMktDataDescription = DepthMktDataDescription().apply {
    exchange(exchange ?: "")
    secType(secType ?: "")
    listingExch(listingExch ?: "")
    serviceDataType(serviceDataType ?: "")
    aggGroup(aggGroup ?: 0)
}
