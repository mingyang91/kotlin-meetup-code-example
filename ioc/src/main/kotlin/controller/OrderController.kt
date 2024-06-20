package ioc.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ioc.service.OrderService
import ioc.service.PaymentService

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService,
    private val paymentService: PaymentService
) {
    @RequestMapping("/update")
    fun updateOrder(): String {
        return "Order updated"
    }
}