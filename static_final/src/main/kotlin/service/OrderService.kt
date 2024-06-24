package service

import javax.sql.DataSource
import repository.OrderRepository

object OrderService {
    context(DataSource, WechatPayService)
    fun cancelOrder() {
        OrderRepository.updateOrder("Cancelled")
        PaymentService.cancelPayment()
    }

    context(DataSource)
    fun completeOrder() {
        OrderRepository.updateOrder("Completed")
    }
}
