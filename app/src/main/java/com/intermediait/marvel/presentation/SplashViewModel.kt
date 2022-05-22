package com.intermediait.marvel.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : ViewModel() {

    private val _ready = MutableLiveData<Boolean>()
    val ready: LiveData<Boolean> get() = _ready

    fun startDelay() {
        viewModelScope.launch {
            delay(5000)
            _ready.postValue(true)
        }
    }
}