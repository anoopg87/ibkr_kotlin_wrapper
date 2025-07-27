package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ContractKT
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AccountsApiTest {
    private lateinit var client: IbClient
    private lateinit var callbacks: CallBacks
    private lateinit var api: AccountsApi

    @BeforeEach
    fun setup() {
        client = mockk(relaxed = true)
        callbacks = CallBacks()
        api = AccountsApi(client, callbacks)
    }

    @Test
    fun `reqAccountUpdates sets callbacks and calls client`() {
        val onUpdateAccountValue = mockk<(String?, String?, String?, String?) -> Unit>(relaxed = true)
        val onUpdateAccountTime = mockk<(String?) -> Unit>(relaxed = true)
        val onAccountDownloadEnd = mockk<(String?) -> Unit>(relaxed = true)
        api.reqAccountUpdates(true, "ACC123", onUpdateAccountValue, onUpdateAccountTime, onAccountDownloadEnd)
        assertEquals(onUpdateAccountValue, callbacks.onUpdateAccountValue)
        assertEquals(onUpdateAccountTime, callbacks.onUpdateAccountTime)
        assertEquals(onAccountDownloadEnd, callbacks.onAccountDownloadEnd)
        verify { client.reqAccountUpdates(true, "ACC123") }
    }

    @Test
    fun `reqPositions sets callbacks and calls client`() {
        val onPosition = mockk<(String, ContractKT, Double, Double) -> Unit>(relaxed = true)
        val onPositionEnd = mockk<() -> Unit>(relaxed = true)
        api.reqPositions(onPosition, onPositionEnd)
        assertEquals(onPositionEnd, callbacks.onPositionEnd)
        verify { client.reqPositions() }
    }
}
