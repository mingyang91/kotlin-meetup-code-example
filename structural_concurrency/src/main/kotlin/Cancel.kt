import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

suspend fun acquire(): Boolean = TODO()
suspend fun release(a: Boolean): Unit = TODO()
suspend fun doSomething(): Unit = TODO()

suspend fun multipleStepTask() {
    val (server, client) = withContext(NonCancellable) {
        Pair(acquire(), acquire())
    }

    try {
        doSomething()
        doSomething()
        doSomething()
    } finally {
        // uninterruptible block
        withContext(NonCancellable) {
            release(server)
            release(client)
        }
    }
}

suspend fun main() = coroutineScope {
    val task = async { multipleStepTask() }
    task.cancel()
}