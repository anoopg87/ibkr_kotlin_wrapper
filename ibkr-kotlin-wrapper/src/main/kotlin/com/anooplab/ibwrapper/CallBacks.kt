package com.anooplab.ibwrapper

import com.anooplab.ibwrapper.api.toDouble
import com.anooplab.ibwrapper.model.*
import com.ib.client.*
import toKT
import java.lang.Exception

class CallBacks : EWrapper {
    /**
     * Callback for tick price updates.
     * @param tickerId The ticker ID
     * @param field Tick type field
     * @param price The price
     * @param attrib Tick attributes
     */
    var onTickPrice: ((tickerId: Int, field: Int, price: Double, attrib: TickAttribKT?) -> Unit)? = null
    override fun tickPrice(tickerId: Int, field: Int, price: Double, attrib: TickAttrib?) {
        onTickPrice?.invoke(tickerId, field, price, attrib?.toKT())
    }

    /**
     * Callback for tick size updates.
     * @param tickerId The ticker ID
     * @param field Tick type field
     * @param size The size (Decimal)
     */
    var onTickSize: ((tickerId: Int, field: Int, size: Double?) -> Unit)? = null
    override fun tickSize(tickerId: Int, field: Int, size: Decimal?) {
        onTickSize?.invoke(tickerId, field, size?.toDouble())
    }

    /**
     * Callback for tick option computation updates.
     * @param tickerId The ticker ID
     * @param field Tick type field
     * @param tickAttrib Option computation fields
     * @param impliedVol Implied volatility
     * @param delta Delta
     * @param optPrice Option price
     * @param pvDividend Present value of dividends
     * @param gamma Gamma
     * @param vega Vega
     * @param theta Theta
     * @param undPrice Underlying price
     * @param price Option price (duplicate for signature match)
     */
    var onTickOptionComputation: ((tickerId: Int, field: Int, tickAttrib: Int, impliedVol: Double, delta: Double, optPrice: Double, pvDividend: Double, gamma: Double, vega: Double, theta: Double, undPrice: Double) -> Unit)? = null
    override fun tickOptionComputation(
        tickerId: Int,
        field: Int,
        tickAttrib: Int,
        impliedVol: Double,
        delta: Double,
        optPrice: Double,
        pvDividend: Double,
        gamma: Double,
        vega: Double,
        theta: Double,
        undPrice: Double
    ) {
        onTickOptionComputation?.invoke(tickerId, field, tickAttrib, impliedVol, delta, optPrice, pvDividend, gamma, vega, theta, undPrice)
    }

    /**
     * Callback for tick generic updates.
     * @param tickerId The ticker ID
     * @param tickType Tick type
     * @param value The value
     */
    var onTickGeneric: ((tickerId: Int, tickType: Int, value: Double) -> Unit)? = null
    override fun tickGeneric(tickerId: Int, tickType: Int, value: Double) {
        onTickGeneric?.invoke(tickerId, tickType, value)
    }

    /**
     * Callback for tick string updates.
     * @param tickerId The ticker ID
     * @param tickType Tick type
     * @param value The string value
     */
    var onTickString: ((tickerId: Int, tickType: Int, value: String?) -> Unit)? = null
    override fun tickString(tickerId: Int, tickType: Int, value: String?) {
        onTickString?.invoke(tickerId, tickType, value)
    }

    /**
     * Callback for tick EFP updates.
     * @param tickerId The ticker ID
     * @param tickType Tick type
     * @param basisPoints Basis points
     * @param formattedBasisPoints Formatted basis points
     * @param impliedFuture Implied future
     * @param holdDays Hold days
     * @param futureLastTradeDate Future last trade date
     * @param dividendImpact Dividend impact
     * @param dividendsToLastTradeDate Dividends to last trade date
     */
    var onTickEFP: ((tickerId: Int, tickType: Int, basisPoints: Double, formattedBasisPoints: String?, impliedFuture: Double, holdDays: Int, futureLastTradeDate: String?, dividendImpact: Double, dividendsToLastTradeDate: Double) -> Unit)? = null
    override fun tickEFP(
        tickerId: Int,
        tickType: Int,
        basisPoints: Double,
        formattedBasisPoints: String?,
        impliedFuture: Double,
        holdDays: Int,
        futureLastTradeDate: String?,
        dividendImpact: Double,
        dividendsToLastTradeDate: Double
    ) {
        onTickEFP?.invoke(tickerId, tickType, basisPoints, formattedBasisPoints, impliedFuture, holdDays, futureLastTradeDate, dividendImpact, dividendsToLastTradeDate)
    }

    /**
     * Callback for order status updates.
     * @param orderId The order ID
     * @param status The order status
     * @param filled The filled amount
     * @param remaining The remaining amount
     * @param avgFillPrice The average fill price
     * @param permId The permanent ID
     * @param parentId The parent order ID
     * @param lastFillPrice The last fill price
     * @param clientId The client ID
     * @param whyHeld Reason held
     * @param mktCapPrice Market cap price
     */
    var onOrderStatus: ((orderId: Int, status: String?, filled: Double?, remaining: Double?, avgFillPrice: Double, permId: Int, parentId: Int, lastFillPrice: Double, clientId: Int, whyHeld: String?, mktCapPrice: Double) -> Unit)? = null
    override fun orderStatus(orderId: Int, status: String?, filled: Decimal?, remaining: Decimal?, avgFillPrice: Double, permId: Int, parentId: Int, lastFillPrice: Double, clientId: Int, whyHeld: String?, mktCapPrice: Double) {
        onOrderStatus?.invoke(orderId, status, filled?.toDouble(), remaining?.toDouble(), avgFillPrice, permId, parentId, lastFillPrice, clientId, whyHeld, mktCapPrice)
    }

    /**
     * Callback for open order event.
     * @param orderId The order ID
     * @param contract The contract
     * @param order The order
     * @param orderState The order state
     */
    var onOpenOrder: ((orderId: Int, contract: ContractKT?, order: OrderKT?, orderState: OrderStateKT?) -> Unit)? = null
    override fun openOrder(orderId: Int, contract: Contract?, order: Order?, orderState: OrderState?) {
        onOpenOrder?.invoke(orderId, contract?.toKT(), order?.toKT(), orderState?.toKT())
    }

    /**
     * Callback for open order end event.
     */
    var onOpenOrderEnd: (() -> Unit)? = null
    override fun openOrderEnd() {
        onOpenOrderEnd?.invoke()
    }

    /**
     * Callback for update account value event.
     * @param key The key
     * @param value The value
     * @param currency The currency
     * @param accountName The account name
     */
    var onUpdateAccountValue: ((key: String?, value: String?, currency: String?, accountName: String?) -> Unit)? = null
    override fun updateAccountValue(key: String?, value: String?, currency: String?, accountName: String?) {
        onUpdateAccountValue?.invoke(key, value, currency, accountName)
    }

    /**
     * Callback for update portfolio event.
     * @param contract The contract
     * @param position The position
     * @param marketPrice The market price
     * @param marketValue The market value
     * @param averageCost The average cost
     * @param unrealizedPNL The unrealized PNL
     * @param realizedPNL The realized PNL
     * @param accountName The account name
     */
    var onUpdatePortfolio: ((contract: Contract?, position: Decimal?, marketPrice: Double, marketValue: Double, averageCost: Double, unrealizedPNL: Double, realizedPNL: Double, accountName: String?) -> Unit)? = null
    override fun updatePortfolio(contract: Contract?, position: Decimal?, marketPrice: Double, marketValue: Double, averageCost: Double, unrealizedPNL: Double, realizedPNL: Double, accountName: String?) {
        onUpdatePortfolio?.invoke(contract, position, marketPrice, marketValue, averageCost, unrealizedPNL, realizedPNL, accountName)
    }

    /**
     * Callback for update account time event.
     * @param time The time
     */
    var onUpdateAccountTime: ((time: String?) -> Unit)? = null
    override fun updateAccountTime(time: String?) {
        onUpdateAccountTime?.invoke(time)
    }

    /**
     * Callback for account download end event.
     * @param accountName The account code
     */
    var onAccountDownloadEnd: ((accountName: String?) -> Unit)? = null
    override fun accountDownloadEnd(accountName: String?) {
        onAccountDownloadEnd?.invoke(accountName)
    }

    /**
     * Callback for next valid order ID.
     * @param orderId The next valid order ID
     */
    var onNextValidId: ((orderId: Int) -> Unit)? = null
    override fun nextValidId(orderId: Int) {
        onNextValidId?.invoke(orderId)
    }

    /**
     * Callback for contract details.
     * @param reqId The request ID
     * @param contractDetails The contract details
     */
    var onContractDetails: ((reqId: Int, contractDetails: ContractDetails?) -> Unit)? = null
    override fun contractDetails(reqId: Int, contractDetails: ContractDetails?) {
        onContractDetails?.invoke(reqId, contractDetails)
    }

    /**
     * Callback for bond contract details.
     * @param reqId The request ID
     * @param contractDetails The bond contract details
     */
    var onBondContractDetails: ((reqId: Int, contractDetails: ContractDetails?) -> Unit)? = null
    override fun bondContractDetails(reqId: Int, contractDetails: ContractDetails?) {
        onBondContractDetails?.invoke(reqId, contractDetails)
    }

    /**
     * Callback for contract details end.
     * @param reqId The request ID
     */
    var onContractDetailsEnd: ((reqId: Int) -> Unit)? = null
    override fun contractDetailsEnd(reqId: Int) {
        onContractDetailsEnd?.invoke(reqId)
    }

    /**
     * Callback for execution details.
     * @param reqId The request ID
     * @param contract The contract
     * @param execution The execution details
     */
    var onExecDetails: ((reqId: Int, contract: Contract?, execution: Execution?) -> Unit)? = null
    override fun execDetails(reqId: Int, contract: Contract?, execution: Execution?) {
        onExecDetails?.invoke(reqId, contract, execution)
    }

    /**
     * Callback for execution details end.
     * @param reqId The request ID
     */
    var onExecDetailsEnd: ((reqId: Int) -> Unit)? = null
    override fun execDetailsEnd(reqId: Int) {
        onExecDetailsEnd?.invoke(reqId)
    }

    /**
     * Callback for market depth update event.
     * @param reqId The request ID
     * @param position The position
     * @param marketDataType The market data type
     * @param operation The operation
     * @param price The price
     * @param size The size
     */
    var onUpdateMktDepth: ((reqId: Int, position: Int, marketDataType: Int, operation: Int, price: Double, size: Decimal?) -> Unit)? = null
    override fun updateMktDepth(reqId: Int, position: Int, marketDataType: Int, operation: Int, price: Double, size: Decimal?) {
        onUpdateMktDepth?.invoke(reqId, position, marketDataType, operation, price, size)
    }

    /**
     * Callback for market depth L2 update event.
     * @param reqId The request ID
     * @param position The position
     * @param marketMaker The market maker
     * @param operation The operation
     * @param side The side
     * @param price The price
     * @param size The size
     * @param isSmartDepth Whether smart depth is enabled
     */
    var onUpdateMktDepthL2: ((reqId: Int, position: Int, marketMaker: String?, operation: Int, side: Int, price: Double, size: Decimal?, isSmartDepth: Boolean) -> Unit)? = null
    override fun updateMktDepthL2(reqId: Int, position: Int, marketMaker: String?, operation: Int, side: Int, price: Double, size: Decimal?, isSmartDepth: Boolean) {
        onUpdateMktDepthL2?.invoke(reqId, position, marketMaker, operation, side, price, size, isSmartDepth)
    }

    /**
     * Callback for news bulletin update event.
     * @param reqId The request ID
     * @param msgId The message ID
     * @param message The message
     * @param origTime The original time
     */
    var onUpdateNewsBulletin: ((reqId: Int, msgId: Int, message: String?, origTime: String?) -> Unit)? = null
    override fun updateNewsBulletin(reqId: Int, msgId: Int, message: String?, origTime: String?) {
        onUpdateNewsBulletin?.invoke(reqId, msgId, message, origTime)
    }

    /**
     * Callback for managed accounts event.
     * @param accounts The accounts string
     */
    var onManagedAccounts: ((accounts: String?) -> Unit)? = null
    override fun managedAccounts(accounts: String?) {
        onManagedAccounts?.invoke(accounts)
    }

    /**
     * Callback for receive FA event.
     * @param faDataType The FA data type
     * @param xml The XML data
     */
    var onReceiveFA: ((faDataType: Int, xml: String?) -> Unit)? = null
    override fun receiveFA(faDataType: Int, xml: String?) {
        onReceiveFA?.invoke(faDataType, xml)
    }

    /**
     * Callback for historical data event.
     * @param reqId The request ID
     * @param bar The bar data
     */
    var onHistoricalData: ((reqId: Int, bar: Bar?) -> Unit)? = null
    override fun historicalData(reqId: Int, bar: Bar?) {
        onHistoricalData?.invoke(reqId, bar)
    }

    /**
     * Callback for scanner parameters event.
     * @param parameters The scanner parameters XML string
     */
    var onScannerParameters: ((parameters: String?) -> Unit)? = null
    override fun scannerParameters(parameters: String?) {
        onScannerParameters?.invoke(parameters)
    }

    /**
     * Callback for scanner data event.
     * @param reqId The request ID
     * @param rank The rank
     * @param contractDetails The contract details
     * @param distance The distance
     * @param benchmark The benchmark
     * @param projection The projection
     * @param legsStr The legs string
     */
    var onScannerData: ((reqId: Int, rank: Int, contractDetails: ContractDetails?, distance: String?, benchmark: String?, projection: String?, legsStr: String?) -> Unit)? = null
    override fun scannerData(reqId: Int, rank: Int, contractDetails: ContractDetails?, distance: String?, benchmark: String?, projection: String?, legsStr: String?) {
        onScannerData?.invoke(reqId, rank, contractDetails, distance, benchmark, projection, legsStr)
    }

    /**
     * Callback for scanner data end event.
     * @param reqId The request ID
     */
    var onScannerDataEnd: ((reqId: Int) -> Unit)? = null
    override fun scannerDataEnd(reqId: Int) {
        onScannerDataEnd?.invoke(reqId)
    }

    /**
     * Callback for real-time bar event.
     * @param reqId The request ID
     * @param time The time
     * @param open The open price
     * @param high The high price
     * @param low The low price
     * @param close The close price
     * @param volume The volume
     * @param count The count
     * @param wap The weighted average price
     */
    var onRealtimeBar: ((reqId: Int, time: Long, open: Double, high: Double, low: Double, close: Double, volume: Decimal?, count: Decimal?, wap: Int) -> Unit)? = null
    override fun realtimeBar(reqId: Int, time: Long, open: Double, high: Double, low: Double, close: Double, volume: Decimal?, count: Decimal?, wap: Int) {
        onRealtimeBar?.invoke(reqId, time, open, high, low, close, volume, count, wap)
    }

    /**
     * Callback for current time event.
     * @param time The current time (epoch seconds)
     */
    var onCurrentTime: ((time: Long) -> Unit)? = null
    override fun currentTime(time: Long) {
        onCurrentTime?.invoke(time)
    }

    /**
     * Callback for fundamental data event.
     * @param reqId The request ID
     * @param data The fundamental data
     */
    var onFundamentalData: ((reqId: Int, data: String?) -> Unit)? = null
    override fun fundamentalData(reqId: Int, data: String?) {
        onFundamentalData?.invoke(reqId, data)
    }

    /**
     * Callback for delta neutral validation event.
     * @param reqId The request ID
     * @param deltaNeutralContract The delta neutral contract
     */
    var onDeltaNeutralValidation: ((reqId: Int, deltaNeutralContract: DeltaNeutralContract?) -> Unit)? = null
    override fun deltaNeutralValidation(reqId: Int, deltaNeutralContract: DeltaNeutralContract?) {
        onDeltaNeutralValidation?.invoke(reqId, deltaNeutralContract)
    }

    /**
     * Callback for tick snapshot end event.
     * @param reqId The request ID
     */
    var onTickSnapshotEnd: ((reqId: Int) -> Unit)? = null
    override fun tickSnapshotEnd(reqId: Int) {
        onTickSnapshotEnd?.invoke(reqId)
    }

    /**
     * Callback for market data type event.
     * @param reqId The request ID
     * @param marketDataType The market data type
     */
    var onMarketDataType: ((reqId: Int, marketDataType: Int) -> Unit)? = null
    override fun marketDataType(reqId: Int, marketDataType: Int) {
        onMarketDataType?.invoke(reqId, marketDataType)
    }

    /**
     * Callback for commission report event.
     * @param commissionReport The commission report
     */
    var onCommissionReport: ((commissionReport: CommissionReport?) -> Unit)? = null
    override fun commissionReport(commissionReport: CommissionReport?) {
        onCommissionReport?.invoke(commissionReport)
    }

    /**
     * Callback for account position updates.
     * @param account Account code
     * @param contract Contract details
     * @param position Position size (Decimal)
     * @param avgCost Average cost
     */
    var onPosition: ((account: String, contract: Contract, position: Decimal, avgCost: Double) -> Unit)? = null
    override fun position(account: String?, contract: Contract?, position: Decimal?, avgCost: Double) {
        if (account != null && contract != null && position != null) {
            onPosition?.invoke(account, contract, position, avgCost)
        }
    }

    /**
     * Callback for position end event.
     */
    var onPositionEnd: (() -> Unit)? = null
    override fun positionEnd() {
        onPositionEnd?.invoke()
    }

    /**
     * Callback for account summary updates.
     * @param reqId The request ID
     * @param account The account code
     * @param tag The tag
     * @param value The value
     * @param currency The currency
     */
    var onAccountSummary: ((reqId: Int, account: String?, tag: String?, value: String?, currency: String?) -> Unit)? = null
    override fun accountSummary(reqId: Int, account: String?, tag: String?, value: String?, currency: String?) {
        onAccountSummary?.invoke(reqId, account, tag, value, currency)
    }

    /**
     * Callback for account summary end.
     * @param reqId The request ID
     */
    var onAccountSummaryEnd: ((reqId: Int) -> Unit)? = null
    override fun accountSummaryEnd(reqId: Int) {
        onAccountSummaryEnd?.invoke(reqId)
    }

    /**
     * Callback for verify message API event.
     * @param apiData The API data
     */
    var onVerifyMessageAPI: ((apiData: String?) -> Unit)? = null
    override fun verifyMessageAPI(apiData: String?) {
        onVerifyMessageAPI?.invoke(apiData)
    }

    /**
     * Callback for verify completed event.
     * @param isSuccessful Whether verification was successful
     * @param errorText The error text
     */
    var onVerifyCompleted: ((isSuccessful: Boolean, errorText: String?) -> Unit)? = null
    override fun verifyCompleted(isSuccessful: Boolean, errorText: String?) {
        onVerifyCompleted?.invoke(isSuccessful, errorText)
    }

    /**
     * Callback for verify and auth message API event.
     * @param apiData The API data
     * @param xyzChallenge The challenge string
     */
    var onVerifyAndAuthMessageAPI: ((apiData: String?, xyzChallenge: String?) -> Unit)? = null
    override fun verifyAndAuthMessageAPI(apiData: String?, xyzChallenge: String?) {
        onVerifyAndAuthMessageAPI?.invoke(apiData, xyzChallenge)
    }

    /**
     * Callback for verify and auth completed event.
     * @param isSuccessful Whether verification was successful
     * @param errorText The error text
     */
    var onVerifyAndAuthCompleted: ((isSuccessful: Boolean, errorText: String?) -> Unit)? = null
    override fun verifyAndAuthCompleted(isSuccessful: Boolean, errorText: String?) {
        onVerifyAndAuthCompleted?.invoke(isSuccessful, errorText)
    }

    /**
     * Callback for display group list event.
     * @param reqId The request ID
     * @param groups The groups string
     */
    var onDisplayGroupList: ((reqId: Int, groups: String?) -> Unit)? = null
    override fun displayGroupList(reqId: Int, groups: String?) {
        onDisplayGroupList?.invoke(reqId, groups)
    }

    /**
     * Callback for display group updated event.
     * @param reqId The request ID
     * @param group The group string
     */
    var onDisplayGroupUpdated: ((reqId: Int, group: String?) -> Unit)? = null
    override fun displayGroupUpdated(reqId: Int, group: String?) {
        onDisplayGroupUpdated?.invoke(reqId, group)
    }

    /**
     * Callback for error event (Exception).
     * @param exception The exception
     */
    var onErrorException: ((exception: Exception?) -> Unit)? = null
    override fun error(exception: Exception?) {
        onErrorException?.invoke(exception)
    }

    /**
     * Callback for error event (String).
     * @param errorMsg The error message
     */
    var onErrorString: ((errorMsg: String?) -> Unit)? = null
    override fun error(errorMsg: String?) {
        onErrorString?.invoke(errorMsg)
    }

    /**
     * Callback for error event (detailed).
     * @param id The request ID
     * @param errorCode The error code
     * @param errorMsg The error message
     * @param advancedOrderRejectJson Advanced order reject JSON (optional)
     */
    var onErrorDetailed: ((id: Int, errorCode: Int, errorMsg: String?, advancedOrderRejectJson: String?) -> Unit)? = null
    override fun error(id: Int, errorCode: Int, errorMsg: String?, advancedOrderRejectJson: String?) {
        onErrorDetailed?.invoke(id, errorCode, errorMsg, advancedOrderRejectJson)
    }

    /**
     * Callback for connection closed event.
     */
    var onConnectionClosed: (() -> Unit)? = null
    override fun connectionClosed() {
        onConnectionClosed?.invoke()
    }

    /**
     * Callback for connect acknowledgement event.
     */
    var onConnectAck: (() -> Unit)? = null
    override fun connectAck() {
        onConnectAck?.invoke()
    }

    /**
     * Callback for position multi event.
     * @param reqId The request ID
     * @param account The account code
     * @param modelCode The model code
     * @param contract The contract
     * @param position The position (Decimal)
     * @param avgCost The average cost
     */
    var onPositionMulti: ((reqId: Int, account: String?, modelCode: String?, contract: Contract?, position: Decimal?, avgCost: Double) -> Unit)? = null
    override fun positionMulti(reqId: Int, account: String?, modelCode: String?, contract: Contract?, position: Decimal?, avgCost: Double) {
        onPositionMulti?.invoke(reqId, account, modelCode, contract, position, avgCost)
    }

    /**
     * Callback for position multi end event.
     * @param reqId The request ID
     */
    var onPositionMultiEnd: ((reqId: Int) -> Unit)? = null
    override fun positionMultiEnd(reqId: Int) {
        onPositionMultiEnd?.invoke(reqId)
    }

    /**
     * Callback for account update multi event.
     * @param reqId The request ID
     * @param account The account code
     * @param modelCode The model code
     * @param key The key
     * @param value The value
     * @param currency The currency
     */
    var onAccountUpdateMulti: ((reqId: Int, account: String?, modelCode: String?, key: String?, value: String?, currency: String?) -> Unit)? = null
    override fun accountUpdateMulti(reqId: Int, account: String?, modelCode: String?, key: String?, value: String?, currency: String?) {
        onAccountUpdateMulti?.invoke(reqId, account, modelCode, key, value, currency)
    }

    /**
     * Callback for account update multi end event.
     * @param reqId The request ID
     */
    var onAccountUpdateMultiEnd: ((reqId: Int) -> Unit)? = null
    override fun accountUpdateMultiEnd(reqId: Int) {
        onAccountUpdateMultiEnd?.invoke(reqId)
    }

    /**
     * Callback for security definition optional parameter event.
     * @param reqId The request ID
     * @param exchange The exchange
     * @param underlyingConId The underlying contract ID
     * @param tradingClass The trading class
     * @param multiplier The contract multiplier
     * @param expirations Set of expiration dates
     * @param strikes Set of strike prices
     */
    var onSecurityDefinitionOptionalParameter: ((reqId: Int, exchange: String?, underlyingConId: Int, tradingClass: String?, multiplier: String?, expirations: MutableSet<String>?, strikes: MutableSet<Double>?) -> Unit)? = null
    override fun securityDefinitionOptionalParameter(
        reqId: Int,
        exchange: String?,
        underlyingConId: Int,
        tradingClass: String?,
        multiplier: String?,
        expirations: MutableSet<String>?,
        strikes: MutableSet<Double>?
    ) {
        onSecurityDefinitionOptionalParameter?.invoke(reqId, exchange, underlyingConId, tradingClass, multiplier, expirations, strikes)
    }

    /**
     * Callback for security definition optional parameter end event.
     * @param reqId The request ID
     */
    var onSecurityDefinitionOptionalParameterEnd: ((reqId: Int) -> Unit)? = null
    override fun securityDefinitionOptionalParameterEnd(reqId: Int) {
        onSecurityDefinitionOptionalParameterEnd?.invoke(reqId)
    }

    /**
     * Callback for soft dollar tiers event.
     * @param reqId The request ID
     * @param tiers Array of soft dollar tiers
     */
    var onSoftDollarTiers: ((reqId: Int, tiers: Array<out SoftDollarTier>?) -> Unit)? = null
    override fun softDollarTiers(reqId: Int, tiers: Array<out SoftDollarTier>?) {
        onSoftDollarTiers?.invoke(reqId, tiers)
    }

    /**
     * Callback for family codes event.
     * @param familyCodes Array of family codes
     */
    var onFamilyCodes: ((familyCodes: Array<out FamilyCode>?) -> Unit)? = null
    override fun familyCodes(familyCodes: Array<out FamilyCode>?) {
        onFamilyCodes?.invoke(familyCodes)
    }

    /**
     * Callback for symbol samples event.
     * @param reqId The request ID
     * @param contractDescriptions Array of contract descriptions
     */
    var onSymbolSamples: ((reqId: Int, contractDescriptions: Array<out ContractDescription>?) -> Unit)? = null
    override fun symbolSamples(reqId: Int, contractDescriptions: Array<out ContractDescription>?) {
        onSymbolSamples?.invoke(reqId, contractDescriptions)
    }

    /**
     * Callback for historical data end event.
     * @param reqId The request ID
     * @param startDateStr Start date string
     * @param endDateStr End date string
     */
    var onHistoricalDataEnd: ((reqId: Int, startDateStr: String?, endDateStr: String?) -> Unit)? = null
    override fun historicalDataEnd(reqId: Int, startDateStr: String?, endDateStr: String?) {
        onHistoricalDataEnd?.invoke(reqId, startDateStr, endDateStr)
    }

    /**
     * Callback for market depth exchanges event.
     * @param depthMktDataDescriptions Array of market depth data descriptions
     */
    var onMktDepthExchanges: ((depthMktDataDescriptions: Array<out DepthMktDataDescription>?) -> Unit)? = null
    override fun mktDepthExchanges(depthMktDataDescriptions: Array<out DepthMktDataDescription>?) {
        onMktDepthExchanges?.invoke(depthMktDataDescriptions)
    }

    /**
     * Callback for tick news event.
     * @param tickerId The ticker ID
     * @param timeStamp The timestamp
     * @param providerCode The provider code
     * @param articleId The article ID
     * @param headline The headline
     * @param extraData The extra data
     */
    var onTickNews: ((tickerId: Int, timeStamp: Long, providerCode: String?, articleId: String?, headline: String?, extraData: String?) -> Unit)? = null
    override fun tickNews(tickerId: Int, timeStamp: Long, providerCode: String?, articleId: String?, headline: String?, extraData: String?) {
        onTickNews?.invoke(tickerId, timeStamp, providerCode, articleId, headline, extraData)
    }

    /**
     * Callback for smart components event.
     * @param reqId The request ID
     * @param smartComponents Map of smart components
     */
    var onSmartComponents: ((reqId: Int, smartComponents: MutableMap<Int, MutableMap.MutableEntry<String, Char>>?) -> Unit)? = null
    override fun smartComponents(reqId: Int, smartComponents: MutableMap<Int, MutableMap.MutableEntry<String, Char>>?) {
        onSmartComponents?.invoke(reqId, smartComponents)
    }

    /**
     * Callback for tick request parameters event.
     * @param tickerId The ticker ID
     * @param minTick The minimum tick
     * @param bboExchange The BBO exchange
     * @param snapshotPermissions The snapshot permissions
     */
    var onTickReqParams: ((tickerId: Int, minTick: Double, bboExchange: String?, snapshotPermissions: Int) -> Unit)? = null
    override fun tickReqParams(tickerId: Int, minTick: Double, bboExchange: String?, snapshotPermissions: Int) {
        onTickReqParams?.invoke(tickerId, minTick, bboExchange, snapshotPermissions)
    }

    /**
     * Callback for news providers event.
     * @param newsProviders Array of news providers
     */
    var onNewsProviders: ((newsProviders: Array<out NewsProvider>?) -> Unit)? = null
    override fun newsProviders(newsProviders: Array<out NewsProvider>?) {
        onNewsProviders?.invoke(newsProviders)
    }

    /**
     * Callback for news article event.
     * @param requestId The request ID
     * @param articleType The article type
     * @param articleText The article text
     */
    var onNewsArticle: ((requestId: Int, articleType: Int, articleText: String?) -> Unit)? = null
    override fun newsArticle(requestId: Int, articleType: Int, articleText: String?) {
        onNewsArticle?.invoke(requestId, articleType, articleText)
    }

    /**
     * Callback for historical news event.
     * @param requestId The request ID
     * @param time The time
     * @param providerCode The provider code
     * @param articleId The article ID
     * @param headline The headline
     */
    var onHistoricalNews: ((requestId: Int, time: String?, providerCode: String?, articleId: String?, headline: String?) -> Unit)? = null
    override fun historicalNews(requestId: Int, time: String?, providerCode: String?, articleId: String?, headline: String?) {
        onHistoricalNews?.invoke(requestId, time, providerCode, articleId, headline)
    }

    /**
     * Callback for historical news end event.
     * @param requestId The request ID
     * @param hasMore Boolean indicating if more news is available
     */
    var onHistoricalNewsEnd: ((requestId: Int, hasMore: Boolean) -> Unit)? = null
    override fun historicalNewsEnd(p0: Int, p1: Boolean) {
        onHistoricalNewsEnd?.invoke(p0, p1)
    }

    /**
     * Callback for head timestamp event.
     * @param reqId The request ID
     * @param headTimestamp The head timestamp
     */
    var onHeadTimestamp: ((reqId: Int, headTimestamp: String?) -> Unit)? = null
    override fun headTimestamp(reqId: Int, headTimestamp: String?) {
        onHeadTimestamp?.invoke(reqId, headTimestamp)
    }

    /**
     * Callback for histogram data event.
     * @param reqId The request ID
     * @param histogram List of histogram entries
     */
    var onHistogramData: ((reqId: Int, histogram: MutableList<HistogramEntry>?) -> Unit)? = null
    override fun histogramData(reqId: Int, histogram: MutableList<HistogramEntry>?) {
        onHistogramData?.invoke(reqId, histogram)
    }

    /**
     * Callback for historical data update event.
     * @param reqId The request ID
     * @param bar The bar data
     */
    var onHistoricalDataUpdate: ((reqId: Int, bar: Bar?) -> Unit)? = null
    override fun historicalDataUpdate(reqId: Int, bar: Bar?) {
        onHistoricalDataUpdate?.invoke(reqId, bar)
    }

    /**
     * Callback for reroute market data request event.
     * @param reqId The request ID
     * @param conId The contract ID
     * @param exchange The exchange
     */
    var onRerouteMktDataReq: ((reqId: Int, conId: Int, exchange: String?) -> Unit)? = null
    override fun rerouteMktDataReq(reqId: Int, conId: Int, exchange: String?) {
        onRerouteMktDataReq?.invoke(reqId, conId, exchange)
    }

    /**
     * Callback for reroute market depth request event.
     * @param reqId The request ID
     * @param conId The contract ID
     * @param exchange The exchange
     */
    var onRerouteMktDepthReq: ((reqId: Int, conId: Int, exchange: String?) -> Unit)? = null
    override fun rerouteMktDepthReq(reqId: Int, conId: Int, exchange: String?) {
        onRerouteMktDepthReq?.invoke(reqId, conId, exchange)
    }

    /**
     * Callback for market rule event.
     * @param marketRuleId The market rule ID
     * @param priceIncrements Array of price increments
     */
    var onMarketRule: ((marketRuleId: Int, priceIncrements: Array<out PriceIncrement>?) -> Unit)? = null
    override fun marketRule(marketRuleId: Int, priceIncrements: Array<out PriceIncrement>?) {
        onMarketRule?.invoke(marketRuleId, priceIncrements)
    }

    /**
     * Callback for PnL event.
     * @param reqId The request ID
     * @param dailyPnL The daily PnL
     * @param unrealizedPnL The unrealized PnL
     * @param realizedPnL The realized PnL
     */
    var onPnl: ((reqId: Int, dailyPnL: Double, unrealizedPnL: Double, realizedPnL: Double) -> Unit)? = null
    override fun pnl(reqId: Int, dailyPnL: Double, unrealizedPnL: Double, realizedPnL: Double) {
        onPnl?.invoke(reqId, dailyPnL, unrealizedPnL, realizedPnL)
    }

    /**
     * Callback for PnL single event.
     * @param reqId The request ID
     * @param pos The position
     * @param dailyPnL The daily PnL
     * @param unrealizedPnL The unrealized PnL
     * @param realizedPnL The realized PnL
     * @param value The value
     */
    var onPnlSingle: ((reqId: Int, pos: Decimal?, dailyPnL: Double, unrealizedPnL: Double, realizedPnL: Double, value: Double) -> Unit)? = null
    override fun pnlSingle(reqId: Int, pos: Decimal?, dailyPnL: Double, unrealizedPnL: Double, realizedPnL: Double, value: Double) {
        onPnlSingle?.invoke(reqId, pos, dailyPnL, unrealizedPnL, realizedPnL, value)
    }

    /**
     * Callback for historical ticks event.
     * @param reqId The request ID
     * @param ticks List of historical ticks
     * @param done Boolean indicating if done
     */
    var onHistoricalTicks: ((reqId: Int, ticks: MutableList<HistoricalTick>?, done: Boolean) -> Unit)? = null
    override fun historicalTicks(reqId: Int, ticks: MutableList<HistoricalTick>?, done: Boolean) {
        onHistoricalTicks?.invoke(reqId, ticks, done)
    }

    /**
     * Callback for historical ticks bid/ask event.
     * @param reqId The request ID
     * @param ticks List of historical tick bid/ask
     * @param done Boolean indicating if done
     */
    var onHistoricalTicksBidAsk: ((reqId: Int, ticks: MutableList<HistoricalTickBidAsk>?, done: Boolean) -> Unit)? = null
    override fun historicalTicksBidAsk(reqId: Int, ticks: MutableList<HistoricalTickBidAsk>?, done: Boolean) {
        onHistoricalTicksBidAsk?.invoke(reqId, ticks, done)
    }

    /**
     * Callback for historical ticks last event.
     * @param reqId The request ID
     * @param ticks List of historical tick last
     * @param done Boolean indicating if done
     */
    var onHistoricalTicksLast: ((reqId: Int, ticks: MutableList<HistoricalTickLast>?, done: Boolean) -> Unit)? = null
    override fun historicalTicksLast(reqId: Int, ticks: MutableList<HistoricalTickLast>?, done: Boolean) {
        onHistoricalTicksLast?.invoke(reqId, ticks, done)
    }

    /**
     * Callback for tick-by-tick all last event.
     * @param reqId The request ID
     * @param tickType The tick type
     * @param time The time
     * @param price The price
     * @param size The size
     * @param attribLast Tick attributes for last
     * @param exchange The exchange
     * @param specialConditions Special conditions
     */
    var onTickByTickAllLast: ((reqId: Int, tickType: Int, time: Long, price: Double, size: Double?, attribLast: TickAttribLastKT?, exchange: String?, specialConditions: String?) -> Unit)? = null
    override fun tickByTickAllLast(reqId: Int, tickType: Int, time: Long, price: Double, size: Decimal?, attribLast: TickAttribLast?, exchange: String?, specialConditions: String?) {
        onTickByTickAllLast?.invoke(reqId, tickType, time, price, size?.toDouble(), attribLast?.toKT(), exchange, specialConditions)
    }

    /**
     * Callback for tick-by-tick bid/ask event.
     * @param reqId The request ID
     * @param time The time
     * @param bidPrice The bid price
     * @param askPrice The ask price
     * @param bidSize The bid size
     * @param askSize The ask size
     * @param tickAttribBidAsk Tick attributes for bid/ask
     */
    var onTickByTickBidAsk: ((reqId: Int, time: Long, bidPrice: Double, askPrice: Double, bidSize: Double?, askSize: Double?, tickAttribBidAsk: TickAttribBidAskKT?) -> Unit)? = null
    override fun tickByTickBidAsk(reqId: Int, time: Long, bidPrice: Double, askPrice: Double, bidSize: Decimal?, askSize: Decimal?, tickAttribBidAsk: TickAttribBidAsk?) {
        onTickByTickBidAsk?.invoke(reqId, time, bidPrice, askPrice, bidSize?.toDouble(), askSize?.toDouble(), tickAttribBidAsk?.toKT())
    }

    /**
     * Callback for tick-by-tick midpoint event.
     * @param reqId The request ID
     * @param time The time
     * @param midPoint The midpoint price
     */
    var onTickByTickMidPoint: ((reqId: Int, time: Long, midPoint: Double) -> Unit)? = null
    override fun tickByTickMidPoint(reqId: Int, time: Long, midPoint: Double) {
        onTickByTickMidPoint?.invoke(reqId, time, midPoint)
    }

    /**
     * Callback for order bound event.
     * @param orderId The order ID
     * @param apiClientId The API client ID
     * @param apiOrderId The API order ID
     */
    var onOrderBound: ((orderId: Long, apiClientId: Int, apiOrderId: Int) -> Unit)? = null
    override fun orderBound(orderId: Long, apiClientId: Int, apiOrderId: Int) {
        onOrderBound?.invoke(orderId, apiClientId, apiOrderId)
    }

    /**
     * Callback for completed order event.
     * @param contract The contract
     * @param order The order
     * @param orderState The order state
     */
    var onCompletedOrder: ((contract: ContractKT?, order: OrderKT?, orderState: OrderStateKT?) -> Unit)? = null
    override fun completedOrder(contract: Contract?, order: Order?, orderState: OrderState?) {
        onCompletedOrder?.invoke(contract?.toKT(), order?.toKT(), orderState?.toKT())
    }

    /**
     * Callback for completed orders end event.
     */
    var onCompletedOrdersEnd: (() -> Unit)? = null
    override fun completedOrdersEnd() {
        onCompletedOrdersEnd?.invoke()
    }

    /**
     * Callback for replace FA end event.
     * @param reqId The request ID
     * @param text The text
     */
    var onReplaceFAEnd: ((reqId: Int, text: String?) -> Unit)? = null
    override fun replaceFAEnd(reqId: Int, text: String?) {
        onReplaceFAEnd?.invoke(reqId, text)
    }

    /**
     * Callback for WSH meta data event.
     * @param reqId The request ID
     * @param data The meta data
     */
    var onWshMetaData: ((reqId: Int, data: String?) -> Unit)? = null
    override fun wshMetaData(reqId: Int, data: String?) {
        onWshMetaData?.invoke(reqId, data)
    }

    /**
     * Callback for WSH event data event.
     * @param reqId The request ID
     * @param data The event data
     */
    var onWshEventData: ((reqId: Int, data: String?) -> Unit)? = null
    override fun wshEventData(reqId: Int, data: String?) {
        onWshEventData?.invoke(reqId, data)
    }

    /**
     * Callback for historical schedule event.
     * @param reqId The request ID
     * @param startDate The start date
     * @param endDate The end date
     * @param timeZone The time zone
     * @param sessions The list of historical sessions
     */
    var onHistoricalSchedule: ((reqId: Int, startDate: String?, endDate: String?, timeZone: String?, sessions: MutableList<HistoricalSession>?) -> Unit)? = null
    override fun historicalSchedule(reqId: Int, startDate: String?, endDate: String?, timeZone: String?, sessions: MutableList<HistoricalSession>?) {
        onHistoricalSchedule?.invoke(reqId, startDate, endDate, timeZone, sessions)
    }

    /**
     * Callback for user info event.
     * @param reqId The request ID
     * @param userInfo The user info
     */
    var onUserInfo: ((reqId: Int, userInfo: String?) -> Unit)? = null
    override fun userInfo(reqId: Int, userInfo: String?) {
        onUserInfo?.invoke(reqId, userInfo)
    }
}