package com.anooplab.ibwrapper.model

import java.io.Serializable

/**
 * Idiomatic Kotlin wrapper for IB API's TickAttrib model.
 */
data class TickAttribKT(
    val canAutoExecute: Boolean = false,
    val pastLimit: Boolean = false,
    val preOpen: Boolean = false
) : Serializable
