
package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ContractKT
import toKT

/**
 * AccountsApi provides account-related API calls for IBKR, using an injected EClient instance.
 * It also allows subscription to relevant account callbacks via the provided CallBacks instance.
 */
class AccountsApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Request account updates for a given account code, with optional callback for account value updates.
     */
    fun reqAccountUpdates(
        subscribe: Boolean,
        accountCode: String,
        onUpdateAccountValue: ((key: String?, value: String?, currency: String?, accountName: String?) -> Unit)? = null,
        onUpdateAccountTime: ((time: String?) -> Unit)? = null,
        onAccountDownloadEnd: ((accountName: String?) -> Unit)? = null
    ) {
        onUpdateAccountValue?.let { callbacks.onUpdateAccountValue = it }
        onUpdateAccountTime?.let { callbacks.onUpdateAccountTime = it }
        onAccountDownloadEnd?.let { callbacks.onAccountDownloadEnd = it }
        client.reqAccountUpdates(subscribe, accountCode)
    }

    // ...existing code...

    /**
     * Request account summary for a given group and tags, with optional callbacks for summary and end.
     */
    fun reqAccountSummary(
        reqId: Int,
        groupName: String,
        tags: String,
        onAccountSummary: ((reqId: Int, account: String?, tag: String?, value: String?, currency: String?) -> Unit)? = null,
        onAccountSummaryEnd: ((reqId: Int) -> Unit)? = null
    ) {
        onAccountSummary?.let { callbacks.onAccountSummary = it }
        onAccountSummaryEnd?.let { callbacks.onAccountSummaryEnd = it }
        client.reqAccountSummary(reqId, groupName, tags)
    }

    // ...existing code...

    /**
     * Request positions for all accounts, with optional callbacks for position and end.
     * Uses idiomatic Kotlin ContractKT for contract.
     */

    fun reqPositions(
        onPosition: ((account: String, contract: ContractKT, position: Double, avgCost: Double) -> Unit)? = null,
        onPositionEnd: (() -> Unit)? = null
    ) {
        if (onPosition != null) {
            callbacks.onPosition = { account, contract, position, avgCost ->
                onPosition(account, contract.toKT(), position.toDouble() ?: 0.0, avgCost)
            }
        }
        onPositionEnd?.let { callbacks.onPositionEnd = it }
        client.reqPositions()
    }

    // ...existing code...

    /**
     * Request position multi for account and model, with optional callbacks for multi and end.
     * Uses idiomatic Kotlin ContractKT for contract.
     */
    fun reqPositionsMulti(
        reqId: Int,
        account: String,
        modelCode: String,
        onPositionMulti: ((reqId: Int, account: String?, modelCode: String?, contract: ContractKT?, position: Double?, avgCost: Double) -> Unit)? = null,
        onPositionMultiEnd: ((reqId: Int) -> Unit)? = null
    ) {
        if (onPositionMulti != null) {
            callbacks.onPositionMulti = { reqId, account, modelCode, contract, position, avgCost ->
                onPositionMulti(reqId, account, modelCode, contract?.toKT(), position?.toDouble(), avgCost)
            }
        }
        onPositionMultiEnd?.let { callbacks.onPositionMultiEnd = it }
        client.reqPositionsMulti(reqId, account, modelCode)
    }

    // ...existing code...

    data class SixTuple(
        val reqId: Int,
        val account: String?,
        val modelCode: String?,
        val contract: ContractKT?,
        val position: Double?,
        val avgCost: Double
    )

    /**
     * Request account updates multi for account and model, with optional callbacks for multi and end.
     */
    fun reqAccountUpdatesMulti(
        reqId: Int,
        account: String,
        modelCode: String,
        ledgerAndNLV: Boolean,
        onAccountUpdateMulti: ((reqId: Int, account: String?, modelCode: String?, key: String?, value: String?, currency: String?) -> Unit)? = null,
        onAccountUpdateMultiEnd: ((reqId: Int) -> Unit)? = null
    ) {
        onAccountUpdateMulti?.let { callbacks.onAccountUpdateMulti = it }
        onAccountUpdateMultiEnd?.let { callbacks.onAccountUpdateMultiEnd = it }
        client.reqAccountUpdatesMulti(reqId, account, modelCode, ledgerAndNLV)
    }

    // ...existing code...

    /**
     * Cancel account summary request.
     */
    fun cancelAccountSummary(reqId: Int) {
        client.cancelAccountSummary(reqId)
    }

    // ...existing code...

    /**
     * Cancel positions request.
     */
    fun cancelPositions() {
        client.cancelPositions()
    }

    // ...existing code...

    /**
     * Cancel position multi request.
     */
    fun cancelPositionsMulti(reqId: Int) {
        client.cancelPositionsMulti(reqId)
    }

    // ...existing code...

    /**
     * Cancel account updates multi request.
     */
    fun cancelAccountUpdatesMulti(reqId: Int) {
        client.cancelAccountUpdatesMulti(reqId)
    }

    // ...existing code...

    /**
     * Request managed accounts string.
     */
    fun reqManagedAccts(onManagedAccounts: ((accounts: String?) -> Unit)? = null) {
        onManagedAccounts?.let { callbacks.onManagedAccounts = it }
        client.reqManagedAccts()
    }


    /**
     * Request current server time.
     */
    fun reqCurrentTime(onCurrentTime: ((time: Long) -> Unit)? = null) {
        onCurrentTime?.let { callbacks.onCurrentTime = it }
        client.reqCurrentTime()
    }

    // ...existing code...

    /**
     * Request new order IDs.
     */
    fun reqIds(numIds: Int) {
        client.reqIds(numIds)
    }

    // ...existing code...

    /**
     * Set server log level.
     */
    fun setServerLogLevel(logLevel: Int) {
        client.setServerLogLevel(logLevel)
    }

    // ...existing code...
}
