package com.sumin.coroutineflow.lesson14

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

suspend fun main() {
    val scope = CoroutineScope(Dispatchers.Default)

    val flow = MutableStateFlow(0)

    val producer = scope.launch {
        delay(500)
        repeat(10) {
            println("Emitted: $it")
            flow.emit(it)
            println("After emit: $it")
            delay(200)
        }
    }

    val consumer = scope.launch {
        flow.collectLatest {
            println("Collecting started: $it")
            delay(5000)
            println("Collecting finished: $it")
        }
    }

    producer.join()
    consumer.join()
}

