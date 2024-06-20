package repository

import javax.sql.DataSource

context(DataSource)
class OrderRepository {
    fun updateOrder(state: String) {
        println("Order updated: $state")
    }
}