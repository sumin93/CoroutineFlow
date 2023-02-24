package com.sumin.coroutineflow.team_score

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sumin.coroutineflow.databinding.ActivityTeamScoreBinding

class TeamScoreActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityTeamScoreBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[TeamScoreViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        setupListeners()
    }

    private fun observeViewModel() {
        viewModel.team1Score.observe(this) {
            binding.team1Score.text = it.toString()
        }
        viewModel.team2Score.observe(this) {
            binding.team2Score.text = it.toString()
        }
    }

    private fun setupListeners() {
        binding.team1Logo.setOnClickListener {
            viewModel.increaseScore1()
        }
        binding.team2Logo.setOnClickListener {
            viewModel.increaseScore2()
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, TeamScoreActivity::class.java)
    }
}
