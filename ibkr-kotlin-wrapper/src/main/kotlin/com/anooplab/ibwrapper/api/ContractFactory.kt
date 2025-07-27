package com.anooplab.ibwrapper.api

import com.ib.client.Contract

/**
 * Sealed class hierarchy for IBKR Contract types, each extending the IB API Contract class directly.
 *
 * ## Sample Usage
 * ```kotlin
 * val stock: Contract = Stock("AAPL", "SMART", "USD")
 * val option: Contract = Option("AAPL", "20250815", 200.0, "C", "SMART", "USD")
 * val future: Contract = Future("ES", "202509", "GLOBEX", "USD")
 * val forex: Contract = Forex("EURUSD")
 * ```
 */
sealed class IbContractType : Contract() {
    /** Stock contract. */
    data class Stock(val symbol_: String, val exchange_: String, val currency_: String) : IbContractType() {
        init {
            setField("symbol", symbol_)
            setField("secType", "STK")
            setField("exchange", exchange_)
            setField("currency", currency_)
        }
    }

    /** Option contract. */
    data class Option(val symbol_: String, val lastTradeDateOrContractMonth_: String, val strike_: Double, val right_: String, val exchange_: String, val currency_: String) : IbContractType() {
        init {
            setField("symbol", symbol_)
            setField("secType", "OPT")
            setField("lastTradeDateOrContractMonth", lastTradeDateOrContractMonth_)
            setField("strike", strike_)
            setField("right", right_)
            setField("exchange", exchange_)
            setField("currency", currency_)
        }
    }

    /** Future contract. */
    data class Future(val symbol_: String, val lastTradeDateOrContractMonth_: String, val exchange_: String, val currency_: String) : IbContractType() {
        init {
            setField("symbol", symbol_)
            setField("secType", "FUT")
            setField("lastTradeDateOrContractMonth", lastTradeDateOrContractMonth_)
            setField("exchange", exchange_)
            setField("currency", currency_)
        }
    }

    /** Forex contract. */
    data class Forex(val pair: String) : IbContractType() {
        init {
            setField("symbol", pair.substring(0, 3))
            setField("secType", "CASH")
            setField("currency", pair.substring(3, 6))
            setField("exchange", "IDEALPRO")
        }
    }

    /** CFD contract. */
    data class Cfd(val symbol_: String, val exchange_: String, val currency_: String) : IbContractType() {
        init {
            setField("symbol", symbol_)
            setField("secType", "CFD")
            setField("exchange", exchange_)
            setField("currency", currency_)
        }
    }

    /** Warrant contract. */
    data class Warrant(val symbol_: String, val lastTradeDateOrContractMonth_: String, val strike_: Double, val right_: String, val exchange_: String, val currency_: String) : IbContractType() {
        init {
            setField("symbol", symbol_)
            setField("secType", "WAR")
            setField("lastTradeDateOrContractMonth", lastTradeDateOrContractMonth_)
            setField("strike", strike_)
            setField("right", right_)
            setField("exchange", exchange_)
            setField("currency", currency_)
        }
    }
}

/** Reflection-based field setter for IB Contract fields (works around Kotlin/Java interop issues) */
internal fun Contract.setField(field: String, value: Any?) =
    this.javaClass.getField(field).set(this, value)
