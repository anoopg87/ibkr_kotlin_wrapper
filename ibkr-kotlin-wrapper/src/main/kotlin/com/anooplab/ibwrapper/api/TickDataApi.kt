package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.TickAttribBidAskKT
import com.anooplab.ibwrapper.model.TickAttribLastKT
import com.ib.client.*
import toKT

/**
 * TickDataApi provides access to tick-by-tick and historical tick data endpoints.
 */
class TickDataApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Request historical ticks, with optional callback. The callback signature must match the EWrapper interface.
     */
    fun reqHistoricalTicks(
        reqId: Int,
        contract: Contract,
        startDateTime: String?,
        endDateTime: String?,
        numberOfTicks: Int,
        whatToShow: String?,
        useRth: Int,
        ignoreSize: Boolean,
        miscOptions: MutableList<TagValue>?,
        onHistoricalTicks: ((reqId: Int, ticks: MutableList<HistoricalTick>?, done: Boolean) -> Unit)? = null,
        onHistoricalTicksBidAsk: ((reqId: Int, ticks: MutableList<HistoricalTickBidAsk>?, done: Boolean) -> Unit)? = null,
        onHistoricalTicksLast: ((reqId: Int, ticks: MutableList<HistoricalTickLast>?, done: Boolean) -> Unit)? = null
    ) {
        onHistoricalTicks?.let { callbacks.onHistoricalTicks = it }
        onHistoricalTicksBidAsk?.let { callbacks.onHistoricalTicksBidAsk = it }
        onHistoricalTicksLast?.let { callbacks.onHistoricalTicksLast = it }
        client.reqHistoricalTicks(reqId, contract.toKT(), startDateTime, endDateTime, numberOfTicks, whatToShow, useRth, ignoreSize, miscOptions)
    }

    /**
     * Request tick-by-tick data, with optional callbacks for all tick types. The callback signature must match the EWrapper interface.
     */
    fun reqTickByTickData(
        reqId: Int,
        contract: Contract,
        tickType: String?,
        numberOfTicks: Int,
        ignoreSize: Boolean,
        onTickByTickAllLast: ((reqId: Int, tickType: Int, time: Long, price: Double, size: Double?, attribs: TickAttribLastKT?, exchange: String?, specialConditions: String?) -> Unit)? = null,
        onTickByTickBidAsk: ((reqId: Int, time: Long, bidPrice: Double, askPrice: Double, bidSize: Double?, askSize: Double?, attribs: TickAttribBidAskKT?) -> Unit)? = null,
        onTickByTickMidPoint: ((reqId: Int, time: Long, midPoint: Double) -> Unit)? = null
    ) {
        onTickByTickAllLast?.let { callbacks.onTickByTickAllLast = it }
        onTickByTickBidAsk?.let { callbacks.onTickByTickBidAsk = it }
        onTickByTickMidPoint?.let { callbacks.onTickByTickMidPoint = it }
        client.reqTickByTickData(reqId, contract.toKT(), tickType, numberOfTicks, ignoreSize)
    }

    /**
     * Cancel tick-by-tick data request.
     */
    fun cancelTickByTickData(reqId: Int) {
        // No cancelTickByTickData in IbClient, use cancelMktData if needed
        client.cancelMktData(reqId)
    }
}
