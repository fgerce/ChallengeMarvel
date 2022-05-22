package com.intermediait.marvel.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intermediait.marvel.domain.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(

) : ViewModel() {
    private val _character: MutableLiveData<Character> = MutableLiveData()
    val character: LiveData<Character> get() = _character

    fun setCharacter(character: Character) {
        _character.postValue(character)
    }
}