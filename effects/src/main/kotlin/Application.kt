import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.h2.jdbcx.JdbcDataSource
import repository.OrderRepository
import repository.PaymentRepository
import service.OrderService
import service.PaymentService
import service.WechatPayService
import java.net.http.HttpClient
import javax.sql.DataSource

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

fun Application.module() {
    setup()
}

fun Application.setup() {
    val dataSource: DataSource = JdbcDataSource()
    val httpClient: HttpClient = HttpClient.newHttpClient()
    val orderRepository = with(dataSource) { OrderRepository() }
    val paymentRepository = with(dataSource) { PaymentRepository() }
    val wechatPayService = with(httpClient) { WechatPayService() }
    val paymentService = with2(paymentRepository, wechatPayService) { PaymentService() }
    val orderService = with(orderRepository) { OrderService() }

    with4(orderService, paymentService, orderRepository, paymentRepository) {
        routing {
            get("/") {
                call.respondText("Hello, world!")
            }

            get("/order") {
                orderService.completeOrder()
                call.respondText("Order completed")
            }

            get("/payment/cancel") {
                paymentService.cancelPayment()
                call.respondText("Payment cancelled")
            }
        }
    }
}


@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
fun <T1, T2, R> with2(t1: T1, t2: T2, block: context(T1, T2)() -> R): R = block(t1, t2)

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
fun <T1, T2, T3, R> with3(t1: T1, t2: T2, t3: T3, block: context(T1, T2, T3)() -> R): R = block(t1, t2, t3)

@Suppress("SUBTYPING_BETWEEN_CONTEXT_RECEIVERS")
fun <T1, T2, T3, T4, R> with4(t1: T1, t2: T2, t3: T3, t4: T4, block: context(T1, T2, T3, T4)() -> R): R = block(t1, t2, t3, t4)
