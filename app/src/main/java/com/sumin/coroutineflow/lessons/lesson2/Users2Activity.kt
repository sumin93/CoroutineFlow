package com.sumin.coroutineflow.lessons.lesson2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sumin.coroutineflow.databinding.ActivityUsers2Binding

class Users2Activity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUsers2Binding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[UsersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonAddUser.setOnClickListener {
            binding.editTextUsername.text.toString()
                .trim()
                .takeIf { it.isNotBlank() }
                ?.let {
                    viewModel.addUser(it)
                }
        }
        viewModel.users.observe(this) {
            binding.textViewUsers.text = it.joinToString()
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, Users2Activity::class.java)
    }
}
