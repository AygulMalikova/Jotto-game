package com.example.jotto_game.game.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.jotto_game.game.data.ExampleItem
import com.example.jotto_game.game.data.WordRepository
import com.example.jotto_game.game.data.WordRoomDatabase
import com.example.jotto_game.game.sharedpreferences.SharedPreferencesWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

private const val LAST_WORD_KEY = "lastwordkey"

class WordViewModel
@Inject constructor(application: Application,
            @Named("secure") var secureSharedPrefs: SharedPreferencesWrapper,
            @Named("notSecure") var sharedPrefs: SharedPreferencesWrapper) : AndroidViewModel(application) {

    private val repository: WordRepository
    val allWords: LiveData<List<ExampleItem>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: ExampleItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}
