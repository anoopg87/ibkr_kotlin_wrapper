package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ExecutionFilterKT
import com.anooplab.ibwrapper.model.toIbApi
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExecutionApiTest {
    private lateinit var client: IbClient
    private lateinit var callbacks: CallBacks
    private lateinit var api: ExecutionApi

    @BeforeEach
    fun setup() {
        client = mockk(relaxed = true)
        callbacks = CallBacks()
        api = ExecutionApi(client, callbacks)
    }

    @Test
    fun `reqExecutions sets callbacks and calls client`() {
        val filter = mockk<ExecutionFilterKT>(relaxed = true)
        val onExecDetails = mockk<(Int, Any?, Any?) -> Unit>(relaxed = true)
        val onExecDetailsEnd = mockk<(Int) -> Unit>(relaxed = true)
        api.reqExecutions(1, filter, onExecDetails, onExecDetailsEnd)
        // Function references cannot be compared directly; check that callbacks are set
        assert(callbacks.onExecDetails != null)
        assert(callbacks.onExecDetailsEnd != null)
        verify { client.reqExecutions(1, filter.toIbApi()) }
    }
}
