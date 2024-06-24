package ioc.service

import ioc.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val paymentService: PaymentService,
) {
    fun cancelOrder() {
        orderRepository.updateOrder("Cancelled")
        paymentService.cancelPayment()
    }

    fun completeOrder() {
        orderRepository.updateOrder("Completed")
    }
}
