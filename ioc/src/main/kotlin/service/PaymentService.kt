package service

import repository.PaymentRepository
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val orderService: OrderService,
) {
    fun completePayment() {
        paymentRepository.updatePayment("Completed")
        orderService.completeOrder()
    }
    fun cancelPayment() {
        paymentRepository.updatePayment("Cancelled")
    }
}