package com.example.jotto_game.game.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * DB table
 * Word - word inputted by user as a guess, number - number of letters similar to hidden word
 */
@Entity(tableName = "word_table")
data class ExampleItem(@PrimaryKey @ColumnInfo(name = "word") val word: String, val number: String)