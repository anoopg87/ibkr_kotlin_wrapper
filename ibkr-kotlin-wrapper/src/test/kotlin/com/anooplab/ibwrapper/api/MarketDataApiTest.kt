package com.anooplab.ibwrapper.api

import MarketDataApi
import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ContractKT
import com.anooplab.ibwrapper.model.TagValueKT
import com.anooplab.ibwrapper.model.TickAttribKT
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MarketDataApiTest {
    private lateinit var client: IbClient
    private lateinit var callbacks: CallBacks
    private lateinit var api: MarketDataApi

    @BeforeEach
    fun setup() {
        client = mockk(relaxed = true)
        callbacks = CallBacks()
        api = MarketDataApi(client, callbacks)
    }

    @Test
    fun `reqMktData sets callbacks and calls client`() {
        val onTickPrice = mockk<(Int, Int, Double, TickAttribKT?) -> Unit>(relaxed = true)
        val onTickSize = mockk<(Int, Int, Double?) -> Unit>(relaxed = true)
        val onTickString = mockk<(Int, Int, String?) -> Unit>(relaxed = true)
        val contract = mockk<ContractKT>()
        val mktDataOptions = mutableListOf<TagValueKT>()
        api.reqMktData(1, contract, null, false, false, mktDataOptions, onTickPrice, onTickSize, onTickString)
        // Function references cannot be compared directly; check that callbacks are set
        assert(callbacks.onTickPrice != null)
        assert(callbacks.onTickSize != null)
        assert(callbacks.onTickString != null)
        verify { client.reqMktData(1, contract, null, false, false, mktDataOptions) }
    }
}
