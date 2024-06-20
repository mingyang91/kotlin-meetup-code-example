import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import repository.OrderRepository
import repository.PaymentRepository
import service.OrderService
import service.PaymentService
import service.WechatPayService
import java.net.http.HttpClient
import javax.sql.DataSource

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
    }
}

fun wire() {
    val dataSource: DataSource = null!!
    val httpClient: HttpClient = null!!
    val orderRepository = with(dataSource) { OrderRepository() }
    val paymentRepository = with(dataSource) { PaymentRepository() }
    val wechatPayService = with(httpClient) { WechatPayService() }
    val paymentService = with(paymentRepository) {
        with(wechatPayService) {
            PaymentService()
        }
    }

    val orderService = with(orderRepository) { OrderService() }
}

