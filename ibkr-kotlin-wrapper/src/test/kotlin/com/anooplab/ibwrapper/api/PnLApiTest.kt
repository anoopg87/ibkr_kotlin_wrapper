package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PnLApiTest {
    private lateinit var client: IbClient
    private lateinit var callbacks: CallBacks
    private lateinit var api: PnLApi

    @BeforeEach
    fun setup() {
        client = mockk(relaxed = true)
        callbacks = CallBacks()
        api = PnLApi(client, callbacks)
    }

    @Test
    fun `reqPnL sets callback and calls client`() {
        val onPnl = mockk<(Int, Double, Double, Double) -> Unit>(relaxed = true)
        api.reqPnL(1, "account", "model", onPnl)
        assertEquals(onPnl, callbacks.onPnl)
        verify { client.reqPnL(1, "account", "model") }
    }

    @Test
    fun `cancelPnL calls client`() {
        api.cancelPnL(1)
        verify { client.cancelPnL(1) }
    }
}
