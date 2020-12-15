package com.example.jotto_game.game.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

/**
 * Repository for storing the words that user tried as variant while guessing
 */
class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<ExampleItem>> = wordDao.getWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: ExampleItem) {
        wordDao.insert(word)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun reset() {
        wordDao.deleteAll()
    }
}
