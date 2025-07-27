package com.anooplab.ibwrapper.model

import com.ib.client.ExecutionFilter
import com.anooplab.ibwrapper.model.ExecutionFilterKT

fun ExecutionFilter.toKT(): ExecutionFilterKT = ExecutionFilterKT(
    clientId = clientId(),
    acctCode = acctCode(),
    time = time(),
    symbol = symbol(),
    secType = secType(),
    exchange = exchange(),
    side = side()
)

fun ExecutionFilterKT.toIbApi(): ExecutionFilter = ExecutionFilter().apply {
    clientId(clientId ?: 0)
    acctCode(acctCode ?: "")
    time(time ?: "")
    symbol(symbol ?: "")
    secType(secType ?: "")
    exchange(exchange ?: "")
    side(side ?: "")
}
