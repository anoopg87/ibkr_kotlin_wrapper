package com.anooplab.ibwrapper.model

import com.ib.client.TickAttrib

import com.ib.client.TickAttribLast
import com.ib.client.TickAttribBidAsk

fun TickAttrib.toKT(): TickAttribKT = TickAttribKT(
    canAutoExecute = canAutoExecute(),
    pastLimit = pastLimit(),
    preOpen = preOpen()
)

fun TickAttribKT.toIbApi(): TickAttrib = TickAttrib().apply {
    canAutoExecute(canAutoExecute)
    pastLimit(pastLimit)
    preOpen(preOpen)
}

/**
 * Idiomatic Kotlin model for IBKR TickAttribLast.
 */
data class TickAttribLastKT(
    val pastLimit: Boolean = false,
    val unreported: Boolean = false
)

fun TickAttribLast?.toKT(): TickAttribLastKT? = this?.let {
    TickAttribLastKT(
        pastLimit = it.pastLimit(),
        unreported = it.unreported()
    )
}

/**
 * Idiomatic Kotlin model for IBKR TickAttribBidAsk.
 */
data class TickAttribBidAskKT(
    val bidPastLow: Boolean = false,
    val askPastHigh: Boolean = false
)

fun TickAttribBidAsk?.toKT(): TickAttribBidAskKT? = this?.let {
    TickAttribBidAskKT(
        bidPastLow = it.bidPastLow(),
        askPastHigh = it.askPastHigh()
    )
}
