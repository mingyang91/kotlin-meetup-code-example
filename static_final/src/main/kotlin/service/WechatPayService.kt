package service

import java.net.http.HttpClient

context(HttpClient)
class WechatPayService {
    fun pay() {
        println("Payment completed")
    }

    fun refund() {
        println("Payment refunded")
    }
}