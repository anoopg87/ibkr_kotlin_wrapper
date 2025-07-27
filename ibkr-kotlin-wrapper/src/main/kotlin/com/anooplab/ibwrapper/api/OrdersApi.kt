package com.anooplab.ibwrapper.api

import com.anooplab.ibwrapper.CallBacks
import com.anooplab.ibwrapper.client.IbClient
import com.anooplab.ibwrapper.model.ContractKT
import com.anooplab.ibwrapper.model.OrderKT
import com.anooplab.ibwrapper.model.OrderStateKT

/**
 * OrdersApi is used to manage and place orders in the IB API.
 *
 * @property client The IB client instance used to communicate with the IB API.
 * @property callbacks The callbacks instance used to handle asynchronous events.
 */
class OrdersApi(
    private val client: IbClient,
    private val callbacks: CallBacks
) {
    /**
     * Place an order, with optional callback for order status.
     *
     * @param orderId The ID of the order.
     * @param contract The contract details for the order.
     * @param order The order details.
     * @param onOrderStatus Optional callback invoked with the order status updates.
     */
    fun placeOrder(
        orderId: Int,
        contract: ContractKT,
        order: OrderKT,
        onOrderStatus: ((orderId: Int, status: String?, filled: Double?, remaining: Double?, avgFillPrice: Double, permId: Int, parentId: Int, lastFillPrice: Double, clientId: Int, whyHeld: String?, mktCapPrice: Double) -> Unit)? = null,
        onOpenOrder: ((orderId: Int, contract: ContractKT?, order: OrderKT?, orderState: OrderStateKT?) -> Unit)? = null
    ) {
        onOrderStatus?.let { callbacks.onOrderStatus = it }
        onOpenOrder?.let { callbacks.onOpenOrder = it }
        client.placeOrder(orderId, contract, order)
    }

    /**
     * Cancel an order, with optional callback for order status.
     */
    fun cancelOrder(
        orderId: Int,
        onOrderStatus: ((orderId: Int, status: String?, filled: Double?, remaining: Double?, avgFillPrice: Double, permId: Int, parentId: Int, lastFillPrice: Double, clientId: Int, whyHeld: String?, mktCapPrice: Double) -> Unit)? = null
    ) {
        onOrderStatus?.let { callbacks.onOrderStatus = it }
        client.cancelOrder(orderId)
    }

    /**
     * Request open orders, with optional callback for open order events.
     */
    fun reqOpenOrders(
        onOpenOrder: ((orderId: Int, contract: ContractKT?, order: OrderKT?, orderState: OrderStateKT?) -> Unit)? = null,
        onOpenOrderEnd: (() -> Unit)? = null
    ) {
        onOpenOrder?.let { callbacks.onOpenOrder = it }
        onOpenOrderEnd?.let { callbacks.onOpenOrderEnd = it }
        client.reqOpenOrders()
    }

    /**
     * Cancel all open orders for all clients.
     */
    fun reqAllOpenOrders(
        onOpenOrder: ((orderId: Int, contract: ContractKT?, order: OrderKT?, orderState: OrderStateKT?) -> Unit)? = null,
        onOpenOrderEnd: (() -> Unit)? = null
    ) {
        onOpenOrder?.let { callbacks.onOpenOrder = it }
        onOpenOrderEnd?.let { callbacks.onOpenOrderEnd = it }
        client.reqAllOpenOrders()
    }

    /**
     * Request auto open orders.
     */
    fun reqAutoOpenOrders(bAutoBind: Boolean) {
        client.reqAutoOpenOrders(bAutoBind)
    }

    /**
     * Request completed orders, with optional callback for completed orders and end.
     */
    fun reqCompletedOrders(
        apiOnly: Boolean,
        onCompletedOrder: ((contract: ContractKT?, order: OrderKT?, orderState: OrderStateKT?) -> Unit)? = null,
        onCompletedOrdersEnd: (() -> Unit)? = null
    ) {
        onCompletedOrder?.let { callbacks.onCompletedOrder = it }
        onCompletedOrdersEnd?.let { callbacks.onCompletedOrdersEnd = it }
        client.reqCompletedOrders(apiOnly)
    }
}
