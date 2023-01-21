package com.sumin.coroutineflow.lessons.lesson3

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main() {

    val result = getFlowByBuilderFlow().filter { it.isPrime() }
        .filter { it > 20 }
        .map {
            println("Map")
            "Number: $it"
        }
        .last()
    println(result)
}

fun getFlowByFlowOfBuilder(): Flow<Int> {
    return flowOf(3, 4, 8, 16, 5, 7, 11, 32, 41, 28, 43, 47, 84, 116, 53, 59, 61)
}

fun getFlowByBuilderFlow(): Flow<Int> {
    val firstFlow = getFlowByFlowOfBuilder()
    return flow {
//        firstFlow.collect {
//            println("Emitted from first flow: $it")
//            emit(it)
//        }
//        emitAll(firstFlow)
        var i = 0
        while (true) {
            emit(i++)
        }
    }
}

suspend fun Int.isPrime(): Boolean {
    if (this <= 1) return false
    for (i in 2..this / 2) {
        delay(50)
        if (this % i == 0) return false
    }
    return true
}
