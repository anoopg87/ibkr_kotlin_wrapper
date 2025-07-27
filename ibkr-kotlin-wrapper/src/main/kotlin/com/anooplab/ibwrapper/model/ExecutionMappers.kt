package com.anooplab.ibwrapper.model

import com.ib.client.Execution
import com.anooplab.ibwrapper.api.toDoubleSafe
import com.ib.client.Decimal

fun Execution.toKT(): ExecutionKT = ExecutionKT(
    execId = execId(),
    time = time(),
    acctNumber = acctNumber(),
    exchange = exchange(),
    side = side(),
    shares = (shares() as Decimal).toDoubleSafe(),
    price = price(),
    permId = permId(),
    clientId = clientId(),
    orderId = orderId(),
    liquidation = liquidation(),
    cumQty = (cumQty() as Decimal).toDoubleSafe(),
    avgPrice = avgPrice(),
    orderRef = orderRef(),
    evRule = evRule(),
    evMultiplier = evMultiplier()
)

fun ExecutionKT.toIbApi(): Execution = Execution().apply {
    execId(execId ?: "")
    time(time ?: "")
    acctNumber(acctNumber ?: "")
    exchange(exchange ?: "")
    side(side ?: "")
    shares((shares ?: 0.0).toDecimal())
    price(price ?: 0.0)
    permId(permId ?: 0)
    clientId(clientId ?: 0)
    orderId(orderId ?: 0)
    liquidation(liquidation ?: 0)
    cumQty((cumQty ?: 0.0).toDecimal())
    avgPrice(avgPrice ?: 0.0)
    orderRef(orderRef ?: "")
    evRule(evRule ?: "")
    evMultiplier(evMultiplier ?: 0.0)
}
fun Double.toDecimal(): Decimal = Decimal.get(this)