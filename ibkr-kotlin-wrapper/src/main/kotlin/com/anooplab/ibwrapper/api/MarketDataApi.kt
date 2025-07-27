

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ContractKT
import com.anooplab.ibwrapper.model.TagValueKT
import com.anooplab.ibwrapper.model.TickAttribKT

/**
     * Request market data, with optional callback for tick price.
     *
     * @param tickerId The identifier for the ticker.
     * @param contract The contract details for the security.
     * @param genericTickList A comma-separated list of generic tick types.
     * @param snapshot If true, request a snapshot of the market data.
     * @param regulatorySnapshot If true, request a regulatory snapshot.
     * @param mktDataOptions Optional market data options.
     * @param onTickPrice Optional callback for tick price updates.*/

class MarketDataApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Request market data, with optional callback for tick price.
     *
     * @param tickerId The identifier for the ticker.
     * @param contract The contract details for the security.
     * @param genericTickList A comma-separated list of generic tick types.
     * @param snapshot If true, request a snapshot of the market data.
     * @param regulatorySnapshot If true, request a regulatory snapshot.
     * @param mktDataOptions Optional market data options.
     * @param onTickPrice Optional callback for tick price updates.
     */
    fun reqMktData(
        tickerId: Int,
        contract: ContractKT,
        genericTickList: String?,
        snapshot: Boolean,
        regulatorySnapshot: Boolean,
        mktDataOptions: MutableList<TagValueKT>?,
        onTickPrice: ((tickerId: Int, field: Int, price: Double, attrib: TickAttribKT?) -> Unit)? = null,
        onTickSize: ((tickerId: Int, field: Int, size: Double?) -> Unit)? = null,
        onTickString: ((tickerId: Int, tickType: Int, value: String?) -> Unit)? = null
    ) {
        onTickPrice?.let { cb ->
            callbacks.onTickPrice = { tickerId: Int, field: Int, price: Double, attrib: TickAttribKT? ->
                cb(tickerId, field, price, attrib)
            }
        }
        onTickSize?.let { cb ->
            callbacks.onTickSize = { tickerId: Int, field: Int, size: Double? ->
                cb(tickerId, field, size)
            }
        }
        onTickString?.let { cb -> callbacks.onTickString = cb }
        client.reqMktData(
            tickerId,
            contract,
            genericTickList,
            snapshot,
            regulatorySnapshot,
            mktDataOptions
        )
    }

    /**
     * Cancel market data request.
     */
    fun cancelMktData(tickerId: Int) {
        client.cancelMktData(tickerId)
    }
}
