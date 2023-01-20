package com.sumin.coroutineflow.lessons.lesson2

import kotlinx.coroutines.delay

object UsersRepository {

    private val users = mutableListOf("Nick", "John", "Max")

    suspend fun addUser(user: String) {
        delay(10)
        users.add(user)
    }

    suspend fun loadUsers(): List<String> {
        delay(10)
        return users.toList()
    }
}
