package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ContractKT
import com.anooplab.ibwrapper.model.ContractDetailsKT
import com.anooplab.ibwrapper.model.toIbApi
import com.anooplab.ibwrapper.model.toKT

/**
 * ContractApi is used to retrieve contract details from the IB API.
 *
 * It is implemented using the reference pattern from AccountsApi, with support for
 * per-request callbacks.
 */
class ContractApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Request contract details, with optional callback for contract details.
     *
     * @param reqId The request ID.
     * @param contract The contract to retrieve details for.
     * @param onContractDetails Optional callback invoked with the contract details.
     */
    fun reqContractDetails(
        reqId: Int,
        contract: ContractKT,
        onContractDetails: ((reqId: Int, contractDetails: ContractDetailsKT?) -> Unit)? = null
    ) {
        onContractDetails?.let { cb ->
            callbacks.onContractDetails = { id, details ->
                cb(id, details?.toKT())
            }
        }
        client.reqContractDetails(reqId, contract)
    }

    /**
     * Request contract details, with optional callback for contract details and end.
     */
    fun reqContractDetails(
        reqId: Int,
        contract: ContractKT,
        onContractDetails: ((reqId: Int, contractDetails: ContractDetailsKT?) -> Unit)? = null,
        onContractDetailsEnd: ((reqId: Int) -> Unit)? = null
    ) {
        onContractDetails?.let { cb ->
            callbacks.onContractDetails = { id, details ->
                cb(id, details?.toKT())
            }
        }
        onContractDetailsEnd?.let { callbacks.onContractDetailsEnd = it }
        client.reqContractDetails(reqId, contract)
    }
}
