package ioc.service

import ioc.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderService(
    private val orderRepository: OrderRepository,
//    private val paymentService: PaymentService
) {
    fun cancelOrder(orderId: UUID) {
        // TODO: 更新订单状态
//        orderRepository.updateOrder("Cancelled")
        // TODO: 取消支付
//        paymentService.cancelPayment()
    }

    fun completeOrder() {
        orderRepository.updateOrder("Completed")
    }
}
