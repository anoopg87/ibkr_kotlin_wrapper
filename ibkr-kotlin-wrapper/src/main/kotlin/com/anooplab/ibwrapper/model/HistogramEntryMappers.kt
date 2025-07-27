package com.anooplab.ibwrapper.model

import com.anooplab.ibwrapper.api.toDouble
import com.ib.client.HistogramEntry

fun HistogramEntry.toKT(): PriceIncrementKT = PriceIncrementKT(
    lowEdge = price(),
    increment = size().toDouble(),
)

fun PriceIncrementKT.toHistogramEntry(): HistogramEntry = HistogramEntry(
    lowEdge ?: 0.0, increment?.toDecimal()
)
