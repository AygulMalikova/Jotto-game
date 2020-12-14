package com.example.jotto_game.game.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.jotto_game.game.data.ExampleItem
import com.example.jotto_game.game.data.WordDao

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
