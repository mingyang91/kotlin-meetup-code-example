package service

import repository.OrderRepository

context(OrderRepository)
class OrderService {
    context(PaymentService)
    fun cancelOrder() {
        updateOrder("Cancelled")
        cancelPayment()
    }

    fun completeOrder() {
        updateOrder("Completed")
    }
}
