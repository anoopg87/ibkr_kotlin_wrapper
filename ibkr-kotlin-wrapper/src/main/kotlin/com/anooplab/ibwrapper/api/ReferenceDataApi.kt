package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.FamilyCodeKT
import com.anooplab.ibwrapper.model.SoftDollarTierKT
import com.anooplab.ibwrapper.model.PriceIncrementKT
import com.anooplab.ibwrapper.model.DepthMktDataDescriptionKT
import com.anooplab.ibwrapper.model.toKT
import com.anooplab.ibwrapper.model.ContractDescriptionKT
import com.anooplab.ibwrapper.model.toKT
import com.anooplab.ibwrapper.model.toKT

/**
 * ReferenceDataApi provides access to IBKR reference/lookup endpoints such as family codes, soft dollar tiers, matching symbols, exchanges, and market rules.
 */
class ReferenceDataApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Request soft dollar tiers, with optional callback.
     */
    fun reqSoftDollarTiers(
        reqId: Int,
        onSoftDollarTiers: ((reqId: Int, tiers: Array<out SoftDollarTierKT>?) -> Unit)? = null
    ) {
        onSoftDollarTiers?.let { cb ->
            callbacks.onSoftDollarTiers = { id, tiers ->
                cb(id, tiers?.map { it.toKT() }?.toTypedArray())
            }
        }
        client.reqSoftDollarTiers(reqId)
    }

    /**
     * Request family codes, with optional callback.
     */
    fun reqFamilyCodes(
        onFamilyCodes: ((familyCodes: Array<out FamilyCodeKT>?) -> Unit)? = null
    ) {
        onFamilyCodes?.let { cb ->
            callbacks.onFamilyCodes = { codes ->
                cb(codes?.map { it.toKT() }?.toTypedArray())
            }
        }
        client.reqFamilyCodes()
    }

    /**
     * Request matching symbols, with optional callback.
     */
    fun reqMatchingSymbols(
        reqId: Int,
        pattern: String?,
        onSymbolSamples: ((reqId: Int, contractDescriptions: Array<out ContractDescriptionKT>?) -> Unit)? = null
    ) {
        onSymbolSamples?.let { cb ->
            callbacks.onSymbolSamples = { id: Int, descs: Array<out com.ib.client.ContractDescription>? ->
                cb(id, descs?.map { it.toKT() }?.toTypedArray())
            }
        }
        client.reqMatchingSymbols(reqId, pattern)
    }

    /**
     * Request market depth exchanges, with optional callback.
     */
    fun reqMktDepthExchanges(
        onMktDepthExchanges: ((depthMktDataDescriptions: Array<out DepthMktDataDescriptionKT>?) -> Unit)? = null
    ) {
        onMktDepthExchanges?.let { cb ->
            callbacks.onMktDepthExchanges = { descs: Array<out com.ib.client.DepthMktDataDescription>? ->
                cb(descs?.map { it.toKT() }?.toTypedArray())
            }
        }
        client.reqMktDepthExchanges()
    }

    /**
     * Request smart components, with optional callback.
     */
    fun reqSmartComponents(
        reqId: Int,
        bboExchange: String?,
        onSmartComponents: ((reqId: Int, smartComponents: MutableMap<Int, MutableMap.MutableEntry<String, Char>>?) -> Unit)? = null
    ) {
        onSmartComponents?.let { callbacks.onSmartComponents = it }
        client.reqSmartComponents(reqId, bboExchange)
    }

    /**
     * Request market rule, with optional callback.
     */
    fun reqMarketRule(
        marketRuleId: Int,
        onMarketRule: ((marketRuleId: Int, priceIncrements: Array<out PriceIncrementKT>?) -> Unit)? = null
    ) {
        onMarketRule?.let { cb ->
            callbacks.onMarketRule = { id, increments ->
                cb(id, increments?.map { it.toKT() }?.toTypedArray())
            }
        }
        client.reqMarketRule(marketRuleId)
    }
}
