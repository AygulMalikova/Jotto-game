package com.example.jotto_game.game.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jotto_game.game.domain.WordViewModel
import com.example.jotto_game.GameApplication
import com.example.jotto_game.R
import com.example.jotto_game.finishgame.view.FinishGameActivity
import com.example.jotto_game.game.adapters.WordAdapter
import com.example.jotto_game.game.data.ExampleItem
import com.example.jotto_game.game.service.BackgroundSoundService
import kotlinx.android.synthetic.main.playing_game.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class PlayingGameActivity : AppCompatActivity() {
    private var soundService: BackgroundSoundService? = null

    @Inject lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playing_game)
        val str = generateWord()
        var attempts = 0
        val wordList = ArrayList<ExampleItem>()

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(false)
        recycler_view.adapter = WordAdapter(wordList)

        soundService = BackgroundSoundService(this,
            R.raw.music
        )
        soundService!!.start()

       (application as GameApplication).appComponent.inject(this)

        check_word_button.setOnClickListener {
            val similarCount = checkWord(str)
            if (similarCount == str.length) {
                finishGame(str, attempts, true)
            }
            if (similarCount != -1) {
                attempts += 1
                val item = ExampleItem(word_tocheck.text.toString(), similarCount.toString())
                wordList += item
                recycler_view.adapter?.notifyItemChanged(wordList.size - 1)

                wordViewModel.insert(item)
            }
        }

        give_up_button.setOnClickListener {
            finishGame(str, attempts, false)
            soundService!!.pause();
        }

        wordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { wordList.addAll(it) }
        })
    }

    private fun finishGame(word: String, attempts: Int, flag: Boolean) {
        Toast.makeText(
            this, "Game is finished",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this, FinishGameActivity::class.java)
        //TODO add the values
        intent.putExtra(resources.getString(R.string.sourceWord), word)
        intent.putExtra(resources.getString(R.string.numberOfAttempts),attempts)
        intent.putExtra(resources.getString(R.string.gameResult),flag)

        startActivity(intent)
        this.finish()

    }

    private fun generateWord(): String {
        return "about"
    }


    private fun checkWord(generated: String): Int {
        // TODO: check if the word exists
        var word = word_tocheck.text.toString()
        word = word.toLowerCase(Locale.ROOT)
        when {
            word.isEmpty() -> {
                Toast.makeText(
                    this, "Please, enter the word first",
                    Toast.LENGTH_SHORT
                ).show()
                return -1
            }
            word.length != generated.length -> {
                Toast.makeText(
                    this, "Please, enter the word with length:" +
                            generated.length.toString(), Toast.LENGTH_SHORT
                ).show()
                return -1
            }
            hasSimilarLetters(word) -> {
                Toast.makeText(
                    this, "Word contains similar letters", Toast.LENGTH_SHORT
                ).show()
                return -1
            }
            else -> {
                val hashMap: HashMap<Char, Int> = HashMap<Char, Int>()
                for (i in word.indices) {
                    if (!hashMap.containsKey(word[i]))
                        hashMap[word[i]] = 1
                    else {
                        val value = hashMap[word[i]]
                        hashMap.remove(word[i])
                        if (value != null) {
                            hashMap[word[i]] = value + 1
                        }
                    }
                }
                var counter = 0
                for (i in generated.indices) {
                    if (hashMap.containsKey(generated[i]))
                        counter += 1
                }
                return counter
            }
        }

    }

    private fun hasSimilarLetters(word: String): Boolean {
        val hashMap: HashMap<Char, Int> = HashMap<Char, Int>()
        for (i in word.indices) {
            if (!hashMap.containsKey(word[i]))
                hashMap[word[i]] = 1
            else {
                return true
            }
        }
        return false

    }

    override fun onDestroy() {
        super.onDestroy()
        soundService!!.destroy()
    }
}