package com.anooplab.ibwrapper.client

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.model.*
import com.ib.client.EClientSocket
import com.ib.client.EJavaSignal
import toIbApi

/**
 * Wrapper for EClientSocket and EJavaSignal to simplify IBKR client usage.
 *
 * @property callbacks The CallBacks implementation to receive callbacks.
 */
class IbClient(
    val callbacks: CallBacks
) {
    val signal: EJavaSignal = EJavaSignal()
    val client: EClientSocket = EClientSocket(callbacks, signal)

    /**
     * --- EClientSocket public methods exposed for convenience ---
     */
    /**
     * Connect to TWS or IB Gateway.
     * @param host Hostname or IP address
     * @param port Port number
     * @param clientId Client ID
     */
    fun eConnect(host: String, port: Int, clientId: Int) = client.eConnect(host, port, clientId)

    /**
     * Disconnect from TWS or IB Gateway.
     */
    fun eDisconnect() = client.eDisconnect()

    /**
     * Check if the client is connected.
     * @return true if connected, false otherwise
     */
    fun isConnected(): Boolean = client.isConnected

    /**
     * Set the server log level.
     * @param logLevel The log level (1-5)
     */
    fun setServerLogLevel(logLevel: Int) = client.setServerLogLevel(logLevel)

    /**
     * Request the current server time.
     */
    fun reqCurrentTime() = client.reqCurrentTime()

    /**
     * Request new IDs for orders.
     * @param numIds Number of IDs requested
     */
    fun reqIds(numIds: Int) = client.reqIds(numIds)

    /**
     * Request managed accounts.
     */
    fun reqManagedAccts() = client.reqManagedAccts()

    /**
     * Request Financial Advisor (FA) information.
     * @param faDataType The FA data type (idiomatic KT enum)
     */
    fun requestFA(faDataType: FADataTypeKT) = client.requestFA(faDataType.toIbApi())

    /**
     * Replace Financial Advisor (FA) information (idiomatic KT enum).
     * @param faDataType The FA data type
     * @param faType The FA type enum (KT)
     * @param xml The XML data
     */
    fun replaceFA(faDataType: FADataTypeKT, faType: FADataTypeKT, xml: String) =
        client.replaceFA(faDataType.ibValue, faType.toIbApi(), xml)

    /**
     * Subscribe to account updates.
     * @param subscribe True to subscribe, false to unsubscribe
     * @param accountCode The account code
     */
    fun reqAccountUpdates(subscribe: Boolean, accountCode: String) = client.reqAccountUpdates(subscribe, accountCode)

    /**
     * Request account summary.
     * @param reqId The request ID
     * @param groupName The group name
     * @param tags Comma-separated tags
     */
    fun reqAccountSummary(reqId: Int, groupName: String, tags: String) = client.reqAccountSummary(reqId, groupName, tags)

    /**
     * Cancel account summary request.
     * @param reqId The request ID
     */
    fun cancelAccountSummary(reqId: Int) = client.cancelAccountSummary(reqId)

    /**
     * Request positions for all accounts.
     */
    fun reqPositions() = client.reqPositions()

    /**
     * Cancel positions request.
     */
    fun cancelPositions() = client.cancelPositions()

    /**
     * Request multi-account positions.
     * @param reqId The request ID
     * @param account The account code
     * @param modelCode The model code
     */
    fun reqPositionsMulti(reqId: Int, account: String, modelCode: String) = client.reqPositionsMulti(reqId, account, modelCode)

    /**
     * Cancel multi-account positions request.
     * @param reqId The request ID
     */
    fun cancelPositionsMulti(reqId: Int) = client.cancelPositionsMulti(reqId)

    /**
     * Request multi-account updates.
     * @param reqId The request ID
     * @param account The account code
     * @param modelCode The model code
     * @param ledgerAndNLV Whether to include ledger and NLV
     */
    fun reqAccountUpdatesMulti(reqId: Int, account: String, modelCode: String, ledgerAndNLV: Boolean) = client.reqAccountUpdatesMulti(reqId, account, modelCode, ledgerAndNLV)

    /**
     * Cancel multi-account updates request.
     * @param reqId The request ID
     */
    fun cancelAccountUpdatesMulti(reqId: Int) = client.cancelAccountUpdatesMulti(reqId)

    /**
     * Request market data.
     * @param tickerId The ticker ID
     * @param contract The contract
     * @param genericTickList Comma-separated generic tick types
     * @param snapshot True for snapshot, false for streaming
     * @param regulatorySnapshot True for regulatory snapshot
     * @param mktDataOptions Market data options
     */
    fun reqMktData(tickerId: Int, contract: ContractKT, genericTickList: String?, snapshot: Boolean, regulatorySnapshot: Boolean, mktDataOptions: MutableList<TagValueKT>?) =
        client.reqMktData(tickerId, contract.toIbApi(), genericTickList, snapshot, regulatorySnapshot, mktDataOptions?.map { it.toIbApi() })

    /**
     * Cancel market data request.
     * @param tickerId The ticker ID
     */
    fun cancelMktData(tickerId: Int) = client.cancelMktData(tickerId)

    /**
     * Request market depth data.
     * @param tickerId The ticker ID
     * @param contract The contract
     * @param numRows Number of rows
     * @param isSmartDepth True for smart depth
     * @param mktDepthOptions Market depth options
     */
    fun reqMktDepth(tickerId: Int, contract: ContractKT, numRows: Int, isSmartDepth: Boolean, mktDepthOptions: MutableList<com.ib.client.TagValue>?) =
        client.reqMktDepth(tickerId, contract.toIbApi(), numRows, isSmartDepth, mktDepthOptions)

    /**
     * Cancel market depth request.
     * @param tickerId The ticker ID
     * @param isSmartDepth True for smart depth
     */
    fun cancelMktDepth(tickerId: Int, isSmartDepth: Boolean) = client.cancelMktDepth(tickerId, isSmartDepth)

    /**
     * Request open orders for this client.
     */
    fun reqOpenOrders() = client.reqOpenOrders()

    /**
     * Request all open orders for all clients.
     */
    fun reqAllOpenOrders() = client.reqAllOpenOrders()

    /**
     * Request auto open orders.
     * @param bAutoBind True to automatically bind
     */
    fun reqAutoOpenOrders(bAutoBind: Boolean) = client.reqAutoOpenOrders(bAutoBind)

    /**
     * Place an order.
     * @param orderId The order ID
     * @param contract The contract
     * @param order The order
     */
    fun placeOrder(orderId: Int, contract: ContractKT, order: OrderKT) =
        client.placeOrder(orderId, contract.toIbApi(), order.toIbApi())

    /**
     * Cancel an order by ID.
     * @param orderId The order ID
     */
    fun cancelOrder(orderId: Int) = client.cancelOrder(orderId, null)

    /**
     * Cancel an order with details.
     * @param orderId The order ID
     * @param orderCancel The order cancel details
     */
    fun cancelOrder(orderId: Int, orderCancel: com.ib.client.OrderCancel) = client.cancelOrder(orderId, orderCancel)

    /**
     * Request executions matching a filter.
     * @param reqId The request ID
     * @param filter The execution filter
     */
    fun reqExecutions(reqId: Int, filter: com.ib.client.ExecutionFilter) = client.reqExecutions(reqId, filter)

    /**
     * Request contract details.
     * @param reqId The request ID
     * @param contract The contract
     */
    fun reqContractDetails(reqId: Int, contract: ContractKT) =
        client.reqContractDetails(reqId, contract.toIbApi())

    /**
     * Request scanner parameters XML.
     */
    fun reqScannerParameters() = client.reqScannerParameters()

    /**
     * Request scanner subscription.
     * @param reqId The request ID
     * @param subscription The scanner subscription
     * @param scannerSubscriptionOptions Scanner subscription options
     * @param scannerSubscriptionFilterOptions Scanner subscription filter options
     */
    fun reqScannerSubscription(reqId: Int, subscription: ScannerSubscriptionKT, scannerSubscriptionOptions: MutableList<TagValueKT>?, scannerSubscriptionFilterOptions: MutableList<TagValueKT>?) =
        client.reqScannerSubscription(reqId, subscription.toIbApi(), scannerSubscriptionOptions?.map { it.toIbApi() }?.toMutableList(), scannerSubscriptionFilterOptions?.map { it.toIbApi() }?.toMutableList())

    /**
     * Cancel scanner subscription.
     * @param reqId The request ID
     */
    fun cancelScannerSubscription(reqId: Int) = client.cancelScannerSubscription(reqId)

    /**
     * Request historical data.
     * @param reqId The request ID
     * @param contract The contract
     * @param endDateTime End date/time string
     * @param durationStr Duration string
     * @param barSizeSetting Bar size setting
     * @param whatToShow What to show
     * @param useRTH Use regular trading hours (1/0)
     * @param formatDate Format date (1/0)
     * @param keepUpToDate True to keep up to date
     * @param chartOptions Chart options
     */
    fun reqHistoricalData(reqId: Int, contract: ContractKT, endDateTime: String?, durationStr: String?, barSizeSetting: String?, whatToShow: String?, useRTH: Int, formatDate: Int, keepUpToDate: Boolean, chartOptions: MutableList<com.ib.client.TagValue>?) =
        client.reqHistoricalData(reqId, contract.toIbApi(), endDateTime, durationStr, barSizeSetting, whatToShow, useRTH, formatDate, keepUpToDate, chartOptions)

    /**
     * Cancel historical data request.
     * @param reqId The request ID
     */
    fun cancelHistoricalData(reqId: Int) = client.cancelHistoricalData(reqId)

    /**
     * Request real-time bars.
     * @param reqId The request ID
     * @param contract The contract
     * @param barSize The bar size
     * @param whatToShow What to show
     * @param useRTH Use regular trading hours
     * @param realTimeBarsOptions Real-time bars options
     */
    fun reqRealTimeBars(reqId: Int, contract: ContractKT, barSize: Int, whatToShow: String?, useRTH: Boolean, realTimeBarsOptions: MutableList<com.ib.client.TagValue>?) =
        client.reqRealTimeBars(reqId, contract.toIbApi(), barSize, whatToShow, useRTH, realTimeBarsOptions)

    /**
     * Cancel real-time bars request.
     * @param reqId The request ID
     */
    fun cancelRealTimeBars(reqId: Int) = client.cancelRealTimeBars(reqId)

    /**
     * Request fundamental data.
     * @param reqId The request ID
     * @param contract The contract
     * @param reportType The report type
     * @param fundamentalDataOptions Fundamental data options
     */
    fun reqFundamentalData(reqId: Int, contract: ContractKT, reportType: String?, fundamentalDataOptions: MutableList<com.ib.client.TagValue>?) =
        client.reqFundamentalData(reqId, contract.toIbApi(), reportType, fundamentalDataOptions)

    /**
     * Cancel fundamental data request.
     * @param reqId The request ID
     */
    fun cancelFundamentalData(reqId: Int) = client.cancelFundamentalData(reqId)

    /**
     * Request security definition option parameters.
     * @param reqId The request ID
     * @param underlyingSymbol The underlying symbol
     * @param futFopExchange The exchange
     * @param underlyingSecType The security type
     * @param underlyingConId The underlying contract ID
     */
    fun reqSecDefOptParams(reqId: Int, underlyingSymbol: String?, futFopExchange: String?, underlyingSecType: String?, underlyingConId: Int) = client.reqSecDefOptParams(reqId, underlyingSymbol, futFopExchange, underlyingSecType, underlyingConId)

    /**
     * Request soft dollar tiers.
     * @param reqId The request ID
     */
    fun reqSoftDollarTiers(reqId: Int, onSoftDollarTiers: ((reqId: Int, tiers: Array<out SoftDollarTierKT>?) -> Unit)? = null) {
        onSoftDollarTiers?.let { cb ->
            callbacks.onSoftDollarTiers = { id, tiers ->
                cb(id, tiers?.map { it.toKT() }?.toTypedArray())
            }
        }
        client.reqSoftDollarTiers(reqId)
    }

    /**
     * Request family codes.
     */
    fun reqFamilyCodes(onFamilyCodes: ((familyCodes: Array<out FamilyCodeKT>?) -> Unit)? = null) {
        onFamilyCodes?.let { cb ->
            callbacks.onFamilyCodes = { codes ->
                cb(codes?.map { it.toKT() }?.toTypedArray())
            }
        }
        client.reqFamilyCodes()
    }

    /**
     * Request matching symbols.
     * @param reqId The request ID
     * @param pattern The pattern to match
     */
    fun reqMatchingSymbols(reqId: Int, pattern: String?, onSymbolSamples: ((reqId: Int, contractDescriptions: Array<out ContractDescriptionKT>?) -> Unit)? = null) {
        onSymbolSamples?.let { cb ->
            callbacks.onSymbolSamples = { id, descs ->
                cb(id, descs?.map { it.toKT() }?.toTypedArray())
            }
        }
        client.reqMatchingSymbols(reqId, pattern)
    }

    /**
     * Request market depth exchanges.
     */
    fun reqMktDepthExchanges(onMktDepthExchanges: ((depthMktDataDescriptions: Array<out DepthMktDataDescriptionKT>?) -> Unit)? = null) {
        onMktDepthExchanges?.let { cb ->
            callbacks.onMktDepthExchanges = { descs ->
                cb(descs?.map { it.toKT() }?.toTypedArray())
            }
        }
        client.reqMktDepthExchanges()
    }

    /**
     * Request smart components.
     * @param reqId The request ID
     * @param bboExchange The BBO exchange
     */
    fun reqSmartComponents(reqId: Int, bboExchange: String?) = client.reqSmartComponents(reqId, bboExchange)

    /**
     * Request news providers.
     */
    fun reqNewsProviders() = client.reqNewsProviders()

    /**
     * Request a news article.
     * @param requestId The request ID
     * @param providerCode The provider code
     * @param articleId The article ID
     * @param newsArticleOptions News article options
     */
    fun reqNewsArticle(requestId: Int, providerCode: String?, articleId: String?, newsArticleOptions: MutableList<com.ib.client.TagValue>?) = client.reqNewsArticle(requestId, providerCode, articleId, newsArticleOptions)

    /**
     * Request historical news.
     * @param requestId The request ID
     * @param conId The contract ID
     * @param providerCodes Provider codes
     * @param startDateTime Start date/time
     * @param endDateTime End date/time
     * @param totalResults Total results
     * @param historicalNewsOptions Historical news options
     */
    fun reqHistoricalNews(requestId: Int, conId: Int, providerCodes: String?, startDateTime: String?, endDateTime: String?, totalResults: Int, historicalNewsOptions: MutableList<com.ib.client.TagValue>?) = client.reqHistoricalNews(requestId, conId, providerCodes, startDateTime, endDateTime, totalResults, historicalNewsOptions)

    /**
     * Request head timestamp.
     * @param reqId The request ID
     * @param contract The contract
     * @param whatToShow What to show
     * @param useRTH Use regular trading hours
     * @param formatDate Format date
     */
    fun reqHeadTimestamp(reqId: Int, contract: ContractKT, whatToShow: String?, useRTH: Int, formatDate: Int) =
        client.reqHeadTimestamp(reqId, contract.toIbApi(), whatToShow, useRTH, formatDate)

    /**
     * Request histogram data.
     * @param reqId The request ID
     * @param contract The contract
     * @param useRTH Use regular trading hours
     * @param period The period string
     */
    fun reqHistogramData(reqId: Int, contract: ContractKT, useRTH: Boolean, period: String?) =
        client.reqHistogramData(reqId, contract.toIbApi(), useRTH, period)

    /**
     * Request market rule.
     * @param marketRuleId The market rule ID
     */
    fun reqMarketRule(marketRuleId: Int, onMarketRule: ((marketRuleId: Int, priceIncrements: Array<out PriceIncrementKT>?) -> Unit)? = null) {
        onMarketRule?.let { cb ->
            callbacks.onMarketRule = { id, increments ->
                cb(id, increments?.map { it.toKT() }?.toTypedArray())
            }
        }
        client.reqMarketRule(marketRuleId)
    }

    /**
     * Request PnL data.
     * @param reqId The request ID
     * @param account The account code
     * @param modelCode The model code
     */
    fun reqPnL(reqId: Int, account: String?, modelCode: String?) = client.reqPnL(reqId, account, modelCode)

    /**
     * Cancel PnL request.
     * @param reqId The request ID
     */
    fun cancelPnL(reqId: Int) = client.cancelPnL(reqId)

    /**
     * Request PnL single data.
     * @param reqId The request ID
     * @param account The account code
     * @param modelCode The model code
     * @param conId The contract ID
     */
    fun reqPnLSingle(reqId: Int, account: String?, modelCode: String?, conId: Int) = client.reqPnLSingle(reqId, account, modelCode, conId)

    /**
     * Cancel PnL single request.
     * @param reqId The request ID
     */
    fun cancelPnLSingle(reqId: Int) = client.cancelPnLSingle(reqId)

    /**
     * Request historical ticks.
     * @param reqId The request ID
     * @param contract The contract
     * @param startDateTime Start date/time
     * @param endDateTime End date/time
     * @param numberOfTicks Number of ticks
     * @param whatToShow What to show
     * @param useRth Use regular trading hours
     * @param ignoreSize Ignore size
     * @param miscOptions Miscellaneous options
     */
    fun reqHistoricalTicks(reqId: Int, contract: ContractKT, startDateTime: String?, endDateTime: String?, numberOfTicks: Int, whatToShow: String?, useRth: Int, ignoreSize: Boolean, miscOptions: MutableList<com.ib.client.TagValue>?) =
        client.reqHistoricalTicks(reqId, contract.toIbApi(), startDateTime, endDateTime, numberOfTicks, whatToShow, useRth, ignoreSize, miscOptions)

    /**
     * Request tick-by-tick data.
     * @param reqId The request ID
     * @param contract The contract
     * @param tickType The tick type
     * @param numberOfTicks Number of ticks
     * @param ignoreSize Ignore size
     */
    fun reqTickByTickData(reqId: Int, contract: ContractKT, tickType: String?, numberOfTicks: Int, ignoreSize: Boolean) =
        client.reqTickByTickData(reqId, contract.toIbApi(), tickType, numberOfTicks, ignoreSize)

    /**
     * Request completed orders.
     * @param apiOnly True for API-only orders
     */
    fun reqCompletedOrders(apiOnly: Boolean) = client.reqCompletedOrders(apiOnly)
}
