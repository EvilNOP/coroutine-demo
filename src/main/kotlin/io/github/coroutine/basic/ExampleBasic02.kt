package io.github.coroutine.basic

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    val job = GlobalScope.launch {
        delay(1000L)
        println("World!")
    }

    println("Hello, ")
    job.join()
}
