package ioc.repository

import org.springframework.stereotype.Repository

@Repository
class OrderRepository {
    fun updateOrder(state: String) {
        println("Order updated: $state")
    }
}
