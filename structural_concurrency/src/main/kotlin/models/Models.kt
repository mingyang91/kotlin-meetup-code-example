package models

data class Customer(val id: Int)
data class Product(val id: Int): AutoCloseable { override fun close() = println("Product $id unlocked") }
@JvmInline value class CustomerID(val value: Int)
@JvmInline value class ProductID(val value: Int)
data class EntityNotFoundException(override val message: String): Exception(message)
data class InvoiceCheckFailedException(override val message: String): Exception(message)
