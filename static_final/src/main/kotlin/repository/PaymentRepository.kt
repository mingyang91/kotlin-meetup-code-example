package repository

import javax.sql.DataSource

object PaymentRepository {
    context(DataSource)
    fun updatePayment(state: String) {
        println("Payment updated: $state")
    }
}