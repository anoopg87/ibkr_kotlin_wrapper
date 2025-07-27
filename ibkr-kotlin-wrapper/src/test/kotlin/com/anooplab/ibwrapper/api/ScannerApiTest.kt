package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ContractDetailsKT
import com.anooplab.ibwrapper.model.ScannerSubscriptionKT
import com.anooplab.ibwrapper.model.TagValueKT
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScannerApiTest {
    private lateinit var client: IbClient
    private lateinit var callbacks: CallBacks
    private lateinit var api: ScannerApi

    @BeforeEach
    fun setup() {
        client = mockk(relaxed = true)
        callbacks = CallBacks()
        api = ScannerApi(client, callbacks)
    }

    @Test
    fun `reqScannerSubscription sets callback and calls client`() {
        val subscription = mockk<ScannerSubscriptionKT>()
        val options = mutableListOf<TagValueKT>()
        val filterOptions = mutableListOf<TagValueKT>()
        val onScannerData = mockk<(Int, Int, ContractDetailsKT?, String?, String?, String?, String?) -> Unit>(relaxed = true)
        api.reqScannerSubscription(1, subscription, options, filterOptions, onScannerData)
        // Function references cannot be compared directly; check that callback is set
        assert(callbacks.onScannerData != null)
        verify { client.reqScannerSubscription(1, subscription, options, filterOptions) }
    }
}
