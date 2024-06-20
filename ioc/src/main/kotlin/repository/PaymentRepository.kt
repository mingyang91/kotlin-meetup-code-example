package ioc.repository

import org.springframework.stereotype.Repository

@Repository
class PaymentRepository {
    fun updatePayment(state: String) {
        println("Payment updated")
    }
}