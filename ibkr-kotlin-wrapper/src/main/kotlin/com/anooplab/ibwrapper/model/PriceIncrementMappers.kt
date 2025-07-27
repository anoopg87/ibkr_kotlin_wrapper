package com.anooplab.ibwrapper.model

import com.ib.client.PriceIncrement

fun PriceIncrement.toKT(): PriceIncrementKT = PriceIncrementKT(
    lowEdge = lowEdge(),
    increment = increment(),
)

fun PriceIncrementKT.toIbApi(): PriceIncrement = PriceIncrement().apply {
    lowEdge(lowEdge ?: 0.0)
    increment(increment ?: 0.0)
}
