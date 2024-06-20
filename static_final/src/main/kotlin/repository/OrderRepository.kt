package repository

import javax.sql.DataSource

object OrderRepository {
    context(DataSource)
    fun updateOrder(state: String) {
        println("Order updated: $state")
    }
}