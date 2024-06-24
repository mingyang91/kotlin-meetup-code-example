package repository

import javax.sql.DataSource

context(DataSource)
class PaymentRepository {
    fun updatePayment(state: String) {
        println("Payment updated")
    }
}
