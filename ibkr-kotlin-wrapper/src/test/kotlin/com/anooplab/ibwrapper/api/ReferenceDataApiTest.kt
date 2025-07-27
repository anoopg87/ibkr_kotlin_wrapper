package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.SoftDollarTierKT
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ReferenceDataApiTest {
    private lateinit var client: IbClient
    private lateinit var callbacks: CallBacks
    private lateinit var api: ReferenceDataApi

    @BeforeEach
    fun setup() {
        client = mockk(relaxed = true)
        callbacks = CallBacks()
        api = ReferenceDataApi(client, callbacks)
    }

    @Test
    fun `reqSoftDollarTiers sets callback and calls client`() {
        val onSoftDollarTiers = mockk<(Int, Array<out SoftDollarTierKT>?) -> Unit>(relaxed = true)
        api.reqSoftDollarTiers(1, onSoftDollarTiers)
        // Function references cannot be compared directly; check that callback is set
        assert(callbacks.onSoftDollarTiers != null)
        verify { client.reqSoftDollarTiers(1) }
    }
}
