package com.example.roomwordsample.data.repository

import androidx.annotation.WorkerThread
import com.example.roomwordsample.data.datasource.dao.WordDao
import com.example.roomwordsample.data.model.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word:Word){
        wordDao.insert(word)
    }
}