package com.anooplab.ibwrapper.api

import com.ib.client.Decimal

/**
 * Extension functions for IB API Decimal type to convert to Kotlin numeric types.
 */
fun Decimal.toIntSafe(): Int = (this as? Number)?.toInt() ?: this.toString().toIntOrNull() ?: 0
fun Decimal.toDoubleSafe(): Double = (this as? Number)?.toDouble() ?: this.toString().toDoubleOrNull() ?: 0.0
fun Decimal.toLongSafe(): Long = (this as? Number)?.toLong() ?: this.toString().toLongOrNull() ?: 0L
fun Decimal.toDouble(): Double {
    return if (this == Decimal.ZERO) 0.0 else this.toDoubleSafe()
}