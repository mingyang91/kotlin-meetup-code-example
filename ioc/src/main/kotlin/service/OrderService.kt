package ioc.service

import ioc.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val paymentService: PaymentService
) {
    fun cancelOrder(orderId: UUID) {
        orderRepository.updateOrder("Cancelled")
        paymentService.cancelPayment()
    }

    fun completeOrder() {
        orderRepository.updateOrder("Completed")
    }
}
