package jep428

import models.*
import java.util.concurrent.StructuredTaskScope



private fun downloadInvoice(): String = "Invoice"
private fun findCustomersByIds(customerIds: List<CustomerID>): List<Customer> = customerIds.map { Customer(it.value) }
private fun lockProduct(productId: ProductID): Product = throw EntityNotFoundException("Product not found")
private fun updateInventory(product: Product, diff: Int) = println("Inventory updated for product: $product with -$diff")
private fun checkInvoice(invoice: String): Boolean = true

fun makeGroupOrder(customerIds: List<CustomerID>, productId: ProductID) {
    try {
        StructuredTaskScope.ShutdownOnFailure().use { scope ->
            val invoice = scope.fork { downloadInvoice() }
            scope.fork {
                StructuredTaskScope.ShutdownOnFailure().use { innerScope ->
                    val lockedProduct = innerScope.fork { lockProduct(productId) }
                    val customers = innerScope.fork { findCustomersByIds(customerIds) }

                    innerScope.join().throwIfFailed()

                    updateInventory(lockedProduct.get(), customers.get().size)
                }
            }
            scope.join().throwIfFailed()
            checkInvoice(invoice.get())
        }
    } catch (e: EntityNotFoundException) {
        println("Customer or Product not found")
    } catch (e: InvoiceCheckFailedException) {
        println("Invoice check failed, order cancelled")
    }
}

fun main() {
    makeGroupOrder(listOf(CustomerID(1), CustomerID(2)), ProductID(1))
}