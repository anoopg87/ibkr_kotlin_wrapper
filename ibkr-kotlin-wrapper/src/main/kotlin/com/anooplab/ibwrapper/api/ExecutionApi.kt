package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ExecutionFilterKT
import com.anooplab.ibwrapper.model.toIbApi as execFilterToIbApi
import com.anooplab.ibwrapper.model.ExecutionKT
import com.anooplab.ibwrapper.model.toKT as execToKT
import com.anooplab.ibwrapper.model.ContractKT
import toKT

/**
 * ExecutionApi for requesting and managing execution details.
 */
class ExecutionApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Request executions, with optional callback for exec details and end.
     */
    fun reqExecutions(
        reqId: Int,
        filter: ExecutionFilterKT,
        onExecDetails: ((reqId: Int, contract: ContractKT?, execution: ExecutionKT?) -> Unit)? = null,
        onExecDetailsEnd: ((reqId: Int) -> Unit)? = null
    ) {
        onExecDetails?.let { cb ->
            callbacks.onExecDetails = fun(id, contract, execution) {
                cb(id, contract?.toKT(), execution?.execToKT())
            }
        }
        onExecDetailsEnd?.let { callbacks.onExecDetailsEnd = it }
        client.reqExecutions(reqId, filter.execFilterToIbApi())
    }

    /**
     * Cancel all executions requests (not directly available in IB API, but can be implemented by tracking state).
     */
    fun cancelAllExecutions() {
        // Placeholder for user extension: track and cancel all active reqIds if needed.
    }
}
