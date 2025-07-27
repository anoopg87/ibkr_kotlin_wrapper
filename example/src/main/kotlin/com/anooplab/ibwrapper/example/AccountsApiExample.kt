package com.anooplab.ibwrapper.example

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.api.AccountsApi
import com.ib.client.*

/**
 * Example usage of AccountsApi for IBKR Kotlin Wrapper.
 */
fun main() {

    try {
        // Assume EClientSocket and EJavaSignal are available from IB API
        val callbacks = CallBacks()
        val signal = EJavaSignal()
        val client = EClientSocket(callbacks, signal)
        val accountsApi = AccountsApi(client, callbacks)

        // Connect to TWS or IB Gateway (update host/port/clientId as needed)
        client.eConnect("127.0.0.1", 7497, 123)
        println("isConnected: ${client.isConnected}")

        // Request account summary with inline callback
        accountsApi.reqAccountSummary(
            reqId = 9001,
            groupName = "All",
            tags = "NetLiquidation,TotalCashValue",
            onAccountSummary = { reqId, account, tag, value, currency ->
                println("AccountSummary: reqId=$reqId, account=$account, tag=$tag, value=$value, currency=$currency")
            },
            onAccountSummaryEnd = { reqId ->
                println("AccountSummaryEnd: reqId=$reqId")
            }
        )

        // Request positions with inline callback
        accountsApi.reqPositions(
            onPosition = { account, contract, position, avgCost ->
                println("Position: account=$account, contract=${contract.symbol()}, position=$position, avgCost=$avgCost")
            },
            onPositionEnd = {
                println("PositionEnd")
            }
        )

        callbacks.onErrorString = {
            println("Error: $it")
        }

        // Keep the app running to receive callbacks (simple sleep for demo)
        Thread.sleep(60000)
        client.eDisconnect()
    } catch (ex: Exception) {
        println("An error occurred: ${ex.message}")
    }

}
