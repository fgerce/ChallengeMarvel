package com.intermediait.marvel.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.intermediait.marvel.domain.EventRepository
import com.intermediait.marvel.domain.MyResult
import com.intermediait.marvel.domain.models.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventRepository: EventRepository,
) : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> get() = _viewState

    fun getEvents() {
        _viewState.postValue(ViewState.Loading)
        viewModelScope.launch {
            when (val result = eventRepository.getEvents()) {
                is MyResult.Failure -> {
                    _viewState.postValue(ViewState.Failure(result.exception))
                }
                is MyResult.Success -> {
                    _viewState.postValue(ViewState.Ready)
                    _events.postValue(result.data.asReversed())
                }
            }
        }
    }
}