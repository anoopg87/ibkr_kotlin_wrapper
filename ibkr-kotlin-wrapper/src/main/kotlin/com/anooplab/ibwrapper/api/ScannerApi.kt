
package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ContractDetailsKT
import com.anooplab.ibwrapper.model.ScannerSubscriptionKT
import com.anooplab.ibwrapper.model.TagValueKT
import com.anooplab.ibwrapper.model.toKT

/**
 * ScannerApi is used to request and manage scanner subscriptions,
 * providing real-time market data based on predefined scanner settings.
 *
 * @property client The IbClient instance used to communicate with the IB API.
 * @property callbacks The CallBacks instance used to handle callback functions.
 */
class ScannerApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Request scanner subscription, with optional callback for scanner data.
     *
     * @param reqId The ID of the request, used to identify the request in callbacks.
     * @param subscription The ScannerSubscription object defining the subscription parameters.
     * @param scannerSubscriptionOptions Optional. A list of TagValue objects for additional subscription options.
     * @param scannerSubscriptionFilterOptions Optional. A list of TagValue objects for subscription filter options.
     * @param onScannerData Optional. A callback function that is called when scanner data is received.
     */
    fun reqScannerSubscription(
        reqId: Int,
        subscription: ScannerSubscriptionKT,
        scannerSubscriptionOptions: MutableList<TagValueKT>?,
        scannerSubscriptionFilterOptions: MutableList<TagValueKT>?,
        onScannerData: ((reqId: Int, rank: Int, contractDetails: ContractDetailsKT?, distance: String?, benchmark: String?, projection: String?, legsStr: String?) -> Unit)? = null
    ) {
        onScannerData?.let { cb ->
            callbacks.onScannerData = { reqId, rank, contractDetails, distance, benchmark, projection, legsStr ->
                cb(reqId, rank, contractDetails?.toKT(), distance, benchmark, projection, legsStr)
            }
        }
        client.reqScannerSubscription(
            reqId,
            subscription,
            scannerSubscriptionOptions,
            scannerSubscriptionFilterOptions
        )
    }

    /**
     * Cancel a scanner subscription.
     *
     * @param reqId The ID of the request to cancel.
     */
    fun cancelScannerSubscription(reqId: Int) {
        client.cancelScannerSubscription(reqId)
    }

    /**
     * Request scanner parameters XML.
     */
    fun reqScannerParameters() {
        client.reqScannerParameters()
    }

    /**
     * Cancel all scanner subscriptions.
     */
    fun cancelAllScannerSubscriptions() {
        // There is no direct IB API call for this, but you can track and cancel all active reqIds if needed.
        // This is a placeholder for user extension.
    }

    /**
     * Request scanner subscription status (not directly available in IB API, but can be implemented by tracking state).
     */
    fun getScannerParameters(): String? {
        // This would require parsing the XML returned by reqScannerParameters and storing it.
        // Placeholder for user extension.
        return null
    }

    /**
     * Request scanner data end (not directly available in IB API, but can be implemented by tracking state).
     */
    fun cancelScannerData(reqId: Int) {
        // IB API does not have a direct cancelScannerData, but cancelScannerSubscription is used for this purpose.
        cancelScannerSubscription(reqId)
    }
}
