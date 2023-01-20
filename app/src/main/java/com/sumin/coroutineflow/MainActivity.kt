package com.sumin.coroutineflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sumin.coroutineflow.databinding.ActivityMainBinding
import com.sumin.coroutineflow.lessons.lesson2.UsersActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonUsersActivity.setOnClickListener {
            startActivity(UsersActivity.newIntent(this))
        }
    }
}
