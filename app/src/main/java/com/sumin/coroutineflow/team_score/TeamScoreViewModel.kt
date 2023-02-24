package com.sumin.coroutineflow.team_score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TeamScoreViewModel : ViewModel() {

    private val _team1Score = MutableLiveData(0)
    val team1Score: LiveData<Int> = _team1Score

    private val _team2Score = MutableLiveData(0)
    val team2Score: LiveData<Int> = _team2Score

    fun increaseScore1() {
        val oldValue = team1Score.value ?: 0
        _team1Score.value = oldValue + 1
    }

    fun increaseScore2() {
        val oldValue = team2Score.value ?: 0
        _team2Score.value = oldValue + 1
    }
}
