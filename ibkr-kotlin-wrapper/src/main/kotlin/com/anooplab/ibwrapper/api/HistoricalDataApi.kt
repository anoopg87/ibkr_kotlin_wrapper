package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.ib.client.Bar
import com.ib.client.Contract
import com.ib.client.TagValue
import com.ib.client.HistogramEntry
import toKT

/**
 * HistoricalDataApi is used for requesting historical data with optional per-request callbacks.
 *
 * @property client The IbClient instance used to send requests.
 * @property callbacks The CallBacks instance where callbacks are registered.
 */
class HistoricalDataApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Request historical data, with optional callback for historical data.
     *
     * @param reqId The request ID.
     * @param contract The contract for which to request historical data.
     * @param endDateTime The end date and time for the data request.
     * @param durationStr The duration string (e.g., "1 D", "1 W") for the data request.
     * @param barSizeSetting The bar size setting (e.g., "1 min", "5 mins") for the data request.
     * @param whatToShow The type of data to show (e.g., "TRADES", "MIDPOINT") in the request.
     * @param useRTH Indicates whether to use regular trading hours only.
     * @param formatDate The format for the date in the response.
     * @param keepUpToDate Indicates whether to keep the data up to date.
     * @param chartOptions Optional chart options for the request.
     * @param onHistoricalData Optional callback invoked when historical data is received.
     */
    fun reqHistoricalData(
        reqId: Int,
        contract: Contract,
        endDateTime: String?,
        durationStr: String?,
        barSizeSetting: String?,
        whatToShow: String?,
        useRTH: Int,
        formatDate: Int,
        keepUpToDate: Boolean,
        chartOptions: MutableList<TagValue>?,
        onHistoricalData: ((reqId: Int, bar: Bar?) -> Unit)? = null,
        onHistoricalDataEnd: ((reqId: Int, startDateStr: String?, endDateStr: String?) -> Unit)? = null
    ) {
        onHistoricalData?.let { callbacks.onHistoricalData = it }
        onHistoricalDataEnd?.let { callbacks.onHistoricalDataEnd = it }
        client.reqHistoricalData(reqId, contract.toKT(), endDateTime, durationStr, barSizeSetting, whatToShow, useRTH, formatDate, keepUpToDate, chartOptions)
    }

    /**
     * Cancel historical data request.
     */
    fun cancelHistoricalData(reqId: Int) {
        client.cancelHistoricalData(reqId)
    }

    /**
     * Request head timestamp, with optional callback.
     */
    fun reqHeadTimestamp(
        reqId: Int,
        contract: Contract,
        whatToShow: String?,
        useRTH: Int,
        formatDate: Int,
        onHeadTimestamp: ((reqId: Int, headTimestamp: String?) -> Unit)? = null
    ) {
        onHeadTimestamp?.let { callbacks.onHeadTimestamp = it }
        client.reqHeadTimestamp(reqId, contract.toKT(), whatToShow, useRTH, formatDate)
    }

    /**
     * Request histogram data, with optional callback.
     */
    fun reqHistogramData(
        reqId: Int,
        contract: Contract,
        useRTH: Boolean,
        period: String?,
        onHistogramData: ((reqId: Int, histogram: MutableList<HistogramEntry>?) -> Unit)? = null
    ) {
        onHistogramData?.let { callbacks.onHistogramData = it }
        client.reqHistogramData(reqId, contract.toKT(), useRTH, period)
    }

    /**
     * Cancel histogram data request.
     */
    fun cancelHistogramData(reqId: Int) {
        // No cancelHistogramData in IbClient, use cancelHistoricalData if needed
        client.cancelHistoricalData(reqId)
    }
}
