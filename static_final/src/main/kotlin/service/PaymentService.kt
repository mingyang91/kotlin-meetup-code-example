package service

import repository.PaymentRepository
import javax.sql.DataSource

object PaymentService {
    context(DataSource, WechatPayService)
    fun cancelPayment() {
        refund()
        PaymentRepository.updatePayment("Cancelled")
    }

    context(DataSource)
    fun completePayment() {
        PaymentRepository.updatePayment("Completed")
        OrderService.completeOrder()
    }
}