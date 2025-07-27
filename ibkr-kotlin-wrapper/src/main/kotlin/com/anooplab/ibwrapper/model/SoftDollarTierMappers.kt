package com.anooplab.ibwrapper.model

import com.ib.client.SoftDollarTier

fun SoftDollarTier.toKT(): SoftDollarTierKT = SoftDollarTierKT(
    name = name(),
    value = value(),
    displayName = toString()
)

fun SoftDollarTierKT.toIbApi(): SoftDollarTier = SoftDollarTier(
    name ?: "",
    value ?: "",
    displayName ?: ""
)
