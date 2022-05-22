package com.intermediait.marvel.presentation

sealed class ViewState {
    object Ready: ViewState()
    object Loading: ViewState()
    data class Failure(val exception: Exception): ViewState()
}