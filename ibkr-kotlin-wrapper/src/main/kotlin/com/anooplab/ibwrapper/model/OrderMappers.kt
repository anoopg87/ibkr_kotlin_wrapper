
package com.anooplab.ibwrapper.model

import com.ib.client.Order
import com.ib.client.Decimal
import com.anooplab.ibwrapper.api.toDoubleSafe

fun Order.toKT(): OrderKT = OrderKT(
    orderId = orderId(),
    clientId = clientId(),
    action = action()?.name, // Use enum name as string
    totalQuantity = totalQuantity()?.toDoubleSafe(),
    orderType = orderType()?.name, // Use enum name as string
    lmtPrice = lmtPrice(),
    auxPrice = auxPrice(),
    tif = tif()?.name, // Use enum name as string
    ocaGroup = ocaGroup(),
    account = account(),
    openClose = openClose(),
    origin = origin(),
    orderRef = orderRef(),
    transmit = transmit(),
    parentId = parentId(),
    blockOrder = blockOrder(),
    sweepToFill = sweepToFill(),
    displaySize = displaySize(),
    triggerMethod = triggerMethod()?.ordinal, // Use enum ordinal as int
    outsideRth = outsideRth(),
    hidden = hidden(),
    discretionaryAmt = discretionaryAmt(),
    goodAfterTime = goodAfterTime(),
    goodTillDate = goodTillDate(),
    faGroup = faGroup(),
    faMethod = faMethod()?.name, // Use enum name as string
    faPercentage = faPercentage(),
    // faProfile = faProfile(), // Not present in IB API Order
    modelCode = modelCode()
)

fun OrderKT.toIbApi(): Order = Order().apply {
    orderId(orderId ?: 0)
    clientId(clientId ?: 0)
    action(action ?: "BUY")
    totalQuantity(totalQuantity?.let { Decimal.get(it) } ?: Decimal.ZERO)
    orderType(orderType ?: "MKT")
    lmtPrice(lmtPrice ?: 0.0)
    auxPrice(auxPrice ?: 0.0)
    tif(tif ?: "DAY")
    ocaGroup(ocaGroup ?: "")
    account(account ?: "")
    openClose(openClose ?: "")
    origin(origin ?: 0)
    orderRef(orderRef ?: "")
    transmit(transmit ?: false)
    parentId(parentId ?: 0)
    blockOrder(blockOrder ?: false)
    sweepToFill(sweepToFill ?: false)
    displaySize(displaySize ?: 0)
    triggerMethod(triggerMethod ?: 0)
    outsideRth(outsideRth ?: false)
    hidden(hidden ?: false)
    discretionaryAmt(discretionaryAmt ?: 0.0)
    goodAfterTime(goodAfterTime ?: "")
    goodTillDate(goodTillDate ?: "")
    faGroup(faGroup ?: "")
    faMethod(faMethod ?: "EqualQuantity")
    faPercentage(faPercentage ?: "")
    // faProfile(faProfile ?: "") // Not present in IB API Order
    modelCode(modelCode ?: "")
}
