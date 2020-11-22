package com.example.roomwordsample.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomwordsample.data.repository.WordRepository
import com.example.roomwordsample.data.viewmodel.WordViewModel
import java.lang.IllegalArgumentException

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <S : ViewModel?> create(modelClass: Class<S>): S {

        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")

            return WordViewModel(repository) as S
        }

        throw IllegalArgumentException("Unknown ViewModel class")

    }


}