package com.example.jotto_game.game.data

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.jotto_game.game.data.ExampleItem

@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<ExampleItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: ExampleItem)

    @Query("DELETE FROM word_table")
    fun deleteAll()
}
