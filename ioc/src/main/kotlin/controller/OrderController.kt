package ioc.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ioc.service.OrderService
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService,
) {
    @RequestMapping("/update")
    fun updateOrder(): String {
        return "Order updated"
    }

    @RequestMapping("/cancel")
    fun cancelOrder(@RequestParam id: UUID): String {
        orderService.cancelOrder(id)
        return "Order cancelled"
    }
}
