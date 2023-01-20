package com.sumin.coroutineflow.lessons.lesson2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object UsersRepository {

    private val users = mutableListOf("Nick", "John", "Max")

    suspend fun addUser(user: String) {
        delay(10)
        users.add(user)
    }

    suspend fun loadUsers(): Flow<List<String>> = flow {
        while (true) {
            emit(users.toList())
            delay(500)
        }
    }
}
