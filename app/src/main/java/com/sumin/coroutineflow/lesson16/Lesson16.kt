package com.sumin.coroutineflow.lesson16

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main() {
    loadDataFlow()
        .map { State.Content(it) as State }
        .onStart { emit(State.Loading) }
        .retry(2) {
            delay(1000)
            true
        }
        .catch { emit(State.Error) }
        .collect {
            when (it) {
                is State.Content -> {
                    println("Collected: ${it.value}")
                }
                State.Error -> {
                    println("Something went wrong")
                }
                State.Loading -> {
                    println("Loading...")
                }
            }
        }
}

fun loadDataFlow(): Flow<Int> = flow {
    repeat(5) {
        delay(500)
        emit(it)
    }
    throw RuntimeException("Exception from flow block")
}

sealed class State {

    data class Content(val value: Int) : State()
    object Loading : State()
    object Error : State()
}
