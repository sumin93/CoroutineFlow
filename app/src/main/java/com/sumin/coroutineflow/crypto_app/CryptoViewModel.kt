package com.sumin.coroutineflow.crypto_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository

    private val _state = MutableLiveData<State>(State.Initial)
    val state: LiveData<State> = _state

    init {
        loadData()
    }

    private fun loadData() {
        repository.getCurrencyList()
            .onStart {
                val currentState = _state.value
                if (currentState !is State.Content || currentState.currencyList.isEmpty()) {
                    _state.value = State.Loading
                }
            }
            .filter { it.isNotEmpty() }
            .onEach { _state.value = State.Content(currencyList = it) }
            .launchIn(viewModelScope)
    }
}
