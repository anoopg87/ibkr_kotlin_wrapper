package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.ib.client.Contract
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HistoricalDataApiTest {
    private lateinit var client: IbClient
    private lateinit var callbacks: CallBacks
    private lateinit var api: HistoricalDataApi

    @BeforeEach
    fun setup() {
        client = mockk(relaxed = true)
        callbacks = CallBacks()
        api = HistoricalDataApi(client, callbacks)
    }

    @Test
    fun `reqHistoricalData sets callback and calls client`() {
        val contract = mockk<Contract>(relaxed = true)
        val onHistoricalData = mockk<(Int, com.ib.client.Bar?) -> Unit>(relaxed = true)
        api.reqHistoricalData(1, contract, "20250101", "1 D", "1 min", "TRADES", 1, 1, false, null, onHistoricalData)
        // Function references cannot be compared directly; check that callback is set
        assert(callbacks.onHistoricalData != null)
        verify { client.reqHistoricalData(any(), any(), any(), any(), any(), any(), any(), any(), any(), any()) }
    }
}
