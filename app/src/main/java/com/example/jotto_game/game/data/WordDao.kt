package com.example.jotto_game.game.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Dao for mapping the Word(ExampleItem) obj with the db records
 */
@Dao
interface WordDao {

    @Query("SELECT * from word_table")
    fun getWords(): LiveData<List<ExampleItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: ExampleItem)

    @Query("DELETE FROM word_table")
    fun deleteAll()
}
