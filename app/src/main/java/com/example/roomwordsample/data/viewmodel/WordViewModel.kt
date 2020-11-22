package com.example.roomwordsample.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomwordsample.data.model.Word
import com.example.roomwordsample.data.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository):ViewModel(){

    val allWords:LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word:Word)=viewModelScope.launch{

        repository.insert(word)

    }
}