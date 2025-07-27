package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.ib.client.*

class PnLApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Request PnL, with optional callback for PnL updates.
     *
     * @param reqId The request ID.
     * @param account The account number. Optional.
     * @param modelCode The model code. Optional.
     * @param onPnl Optional callback that is called when PnL updates are received.
     */
    fun reqPnL(
        reqId: Int,
        account: String?,
        modelCode: String?,
        onPnl: ((reqId: Int, dailyPnL: Double, unrealizedPnL: Double, realizedPnL: Double) -> Unit)? = null
    ) {
        onPnl?.let { callbacks.onPnl = it }
        client.reqPnL(reqId, account, modelCode)
    }

    /**
     * Cancel a PnL request.
     *
     * @param reqId The request ID.
     */
    fun cancelPnL(reqId: Int) {
        client.cancelPnL(reqId)
    }

    /**
     * Request PnL single, with optional callback for PnL single updates.
     *
     * @param reqId The request ID.
     * @param account The account number. Optional.
     * @param modelCode The model code. Optional.
     * @param conId The contract ID.
     * @param onPnlSingle Optional callback that is called when PnL single updates are received.
     */
    fun reqPnLSingle(
        reqId: Int,
        account: String?,
        modelCode: String?,
        conId: Int,
        onPnlSingle: ((reqId: Int, pos: Decimal?, dailyPnL: Double, unrealizedPnL: Double, realizedPnL: Double, value: Double) -> Unit)? = null
    ) {
        onPnlSingle?.let { callbacks.onPnlSingle = it }
        client.reqPnLSingle(reqId, account, modelCode, conId)
    }

    /**
     * Cancel a PnL single request.
     *
     * @param reqId The request ID.
     */
    fun cancelPnLSingle(reqId: Int) {
        client.cancelPnLSingle(reqId)
    }
}
