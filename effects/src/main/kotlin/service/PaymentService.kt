package service

import repository.PaymentRepository

context(repository.PaymentRepository, service.WechatPayService)
class PaymentService {
    context(OrderService)
    fun completePayment() {
        updatePayment("Completed")
        completeOrder()
    }

    fun cancelPayment() {
        refund()
        updatePayment("Cancelled")
    }
}