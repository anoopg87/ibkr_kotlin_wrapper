package com.anooplab.ibwrapper.example

import MarketDataApi
import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.api.*
import com.anooplab.ibwrapper.model.*
import com.ib.client.*

fun main() {
    val callbacks = CallBacks()
    val client = IbClient(callbacks)

    // AccountsApi Example
    val accountsApi = AccountsApi(client, callbacks)
    accountsApi.reqAccountSummary(1, "All", "NetLiquidation")
    accountsApi.reqPositions()

    // OrdersApi Example
    val ordersApi = OrdersApi(client, callbacks)
    val contract = ContractConvenience.stock("AAPL")
    val order = OrderKT(action = "BUY", totalQuantity = 1.0, orderType = "MKT")
    ordersApi.placeOrder(1, contract, order)

    // MarketDataApi Example
    val marketDataApi = MarketDataApi(client, callbacks)
    marketDataApi.reqMktData(1, contract, null, false, false, null)

    // ScannerApi Example
    val scannerApi = ScannerApi(client, callbacks)
    val scannerSub = ScannerSubscriptionKT(instrument = "STK", locationCode = "STK.US.MAJOR", scanCode = "TOP_PERC_GAIN")
    scannerApi.reqScannerSubscription(1, scannerSub, null, null)

    // ReferenceDataApi Example
    val referenceDataApi = ReferenceDataApi(client, callbacks)
    referenceDataApi.reqSoftDollarTiers(1)

    // HistoricalDataApi Example
    val histApi = HistoricalDataApi(client, callbacks)
    val ibContract = Contract()
    ibContract.symbol("AAPL"); ibContract.secType("STK"); ibContract.exchange("SMART"); ibContract.currency("USD")
    histApi.reqHistoricalData(1, ibContract, "20250727 16:00:00", "1 D", "1 min", "TRADES", 1, 1, false, null)

    // TickDataApi Example
    val tickDataApi = TickDataApi(client, callbacks)
    tickDataApi.reqHistoricalTicks(1, ibContract, "20250727 09:30:00", "20250727 16:00:00", 100, "TRADES", 1, false, null)

    // NewsApi Example
    val newsApi = NewsApi(client, callbacks)
    newsApi.reqNewsProviders()

    // PnLApi Example
    val pnlApi = PnLApi(client, callbacks)
    pnlApi.reqPnL(1, "DU123456", null)

    // ExecutionApi Example
    val execApi = ExecutionApi(client, callbacks)
    val execFilter = ExecutionFilterKT()
    execApi.reqExecutions(1, execFilter)
}
