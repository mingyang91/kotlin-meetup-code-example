@file:Suppress("RedundantSuspendModifier")

package coroutine
import models.*
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope

private suspend fun downloadInvoice(): String = "Invoice"
private suspend fun findCustomersByIds(customerIds: List<CustomerID>): List<Customer> = customerIds.map { Customer(it.value) }
private suspend fun lockProduct(productId: ProductID): Product = throw EntityNotFoundException("Product not found") // Product(productId.value)
private suspend fun updateInventory(product: Product, diff: Int) = println("Inventory updated for product: $product with -$diff")
private suspend fun checkInvoice(invoice: String): Boolean = true

suspend fun makeGroupOrder(customerIds: List<CustomerID>, productId: ProductID) = supervisorScope {
    try {
        val invoice = async { downloadInvoice() }
        val lockedProduct = async { lockProduct(productId) }
        val customers = async { findCustomersByIds(customerIds) }

        updateInventory(lockedProduct.await(), customers.await().size)

        checkInvoice(invoice.await())
    } catch (e: EntityNotFoundException) {
        println("Customer or Product not found")
    } catch (e: InvoiceCheckFailedException) {
        println("Invoice check failed, order cancelled")
    }
}

suspend fun main() {
    makeGroupOrder(listOf(CustomerID(1), CustomerID(2)), ProductID(1))
}
