package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ContractKT
import com.anooplab.ibwrapper.model.ContractDetailsKT
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ContractApiTest {
    private lateinit var client: IbClient
    private lateinit var callbacks: CallBacks
    private lateinit var api: ContractApi

    @BeforeEach
    fun setup() {
        client = mockk(relaxed = true)
        callbacks = CallBacks()
        api = ContractApi(client, callbacks)
    }

    @Test
    fun `reqContractDetails sets callback and calls client`() {
        val contract = mockk<ContractKT>()
        val onContractDetails = mockk<(Int, ContractDetailsKT?) -> Unit>(relaxed = true)
        api.reqContractDetails(1, contract, onContractDetails)
        // Function references cannot be compared directly; check that callback is set
        assert(callbacks.onContractDetails != null)
        verify { client.reqContractDetails(1, contract) }
    }
}
