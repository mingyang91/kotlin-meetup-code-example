package service

import repository.PaymentRepository

context(PaymentRepository, WechatPayService)
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