package com.anooplab.ibwrapper.model

/**
 * Convenient factory methods for creating ContractKT instances for common security types.
 */
object ContractConvenience {
    fun stock(symbol: String, exchange: String = "SMART", currency: String = "USD"): ContractKT =
        ContractKT(symbol = symbol, secType = "STK", exchange = exchange, currency = currency)

    fun option(symbol: String, lastTradeDateOrContractMonth: String, strike: Double, right: String, exchange: String = "SMART", currency: String = "USD", multiplier: String = "100"): ContractKT =
        ContractKT(symbol = symbol, secType = "OPT", lastTradeDateOrContractMonth = lastTradeDateOrContractMonth, strike = strike, right = right, exchange = exchange, currency = currency, multiplier = multiplier)

    fun future(symbol: String, lastTradeDateOrContractMonth: String, exchange: String = "GLOBEX", currency: String = "USD"): ContractKT =
        ContractKT(symbol = symbol, secType = "FUT", lastTradeDateOrContractMonth = lastTradeDateOrContractMonth, exchange = exchange, currency = currency)

    fun forex(symbol: String, currency: String, exchange: String = "IDEALPRO"): ContractKT =
        ContractKT(symbol = symbol, secType = "CASH", exchange = exchange, currency = currency)

    fun index(symbol: String, exchange: String = "SMART", currency: String = "USD"): ContractKT =
        ContractKT(symbol = symbol, secType = "IND", exchange = exchange, currency = currency)

    fun warrant(symbol: String, lastTradeDateOrContractMonth: String, strike: Double, right: String, exchange: String = "SMART", currency: String = "USD"): ContractKT =
        ContractKT(symbol = symbol, secType = "WAR", lastTradeDateOrContractMonth = lastTradeDateOrContractMonth, strike = strike, right = right, exchange = exchange, currency = currency)
}
