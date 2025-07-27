package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ContractKT
import com.anooplab.ibwrapper.model.OrderKT
import com.anooplab.ibwrapper.model.OrderStateKT
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OrdersApiTest {
    private lateinit var client: IbClient
    private lateinit var callbacks: CallBacks
    private lateinit var api: OrdersApi

    @BeforeEach
    fun setup() {
        client = mockk(relaxed = true)
        callbacks = CallBacks()
        api = OrdersApi(client, callbacks)
    }

    @Test
    fun `placeOrder sets callbacks and calls client`() {
        val onOrderStatus = mockk<(Int, String?, Double?, Double?, Double, Int, Int, Double, Int, String?, Double) -> Unit>(relaxed = true)
        val onOpenOrder = mockk<(Int, ContractKT?, OrderKT?, OrderStateKT?) -> Unit>(relaxed = true)
        val contract = mockk<ContractKT>()
        val order = mockk<OrderKT>()
        api.placeOrder(1, contract, order, onOrderStatus, onOpenOrder)
        assertEquals(onOrderStatus, callbacks.onOrderStatus)
        assertEquals(onOpenOrder, callbacks.onOpenOrder)
        verify { client.placeOrder(1, contract, order) }
    }

    @Test
    fun `cancelOrder sets callback and calls client`() {
        val onOrderStatus = mockk<(Int, String?, Double?, Double?, Double, Int, Int, Double, Int, String?, Double) -> Unit>(relaxed = true)
        api.cancelOrder(1, onOrderStatus)
        assertEquals(onOrderStatus, callbacks.onOrderStatus)
        verify { client.cancelOrder(1) }
    }

    @Test
    fun `reqOpenOrders sets callbacks and calls client`() {
        val onOpenOrder = mockk<(Int, ContractKT?, OrderKT?, OrderStateKT?) -> Unit>(relaxed = true)
        val onOpenOrderEnd = mockk<() -> Unit>(relaxed = true)
        api.reqOpenOrders(onOpenOrder, onOpenOrderEnd)
        assertEquals(onOpenOrder, callbacks.onOpenOrder)
        assertEquals(onOpenOrderEnd, callbacks.onOpenOrderEnd)
        verify { client.reqOpenOrders() }
    }
}
