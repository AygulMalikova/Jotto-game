package com.example.jotto_game.game.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jotto_game.R
import com.example.jotto_game.finishgame.view.FinishGameActivity
import com.example.jotto_game.game.adapters.WordAdapter
import com.example.jotto_game.game.data.ExampleItem
import com.example.jotto_game.game.service.BackgroundSoundService
import kotlinx.android.synthetic.main.playing_game.*
import java.util.*
import kotlin.collections.ArrayList

class PlayingGameActivity : AppCompatActivity() {
    private var soundService: BackgroundSoundService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playing_game)
        val str = generateWord()
        val wordList = ArrayList<ExampleItem>()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(false)
        recycler_view.adapter = WordAdapter(wordList)

        soundService = BackgroundSoundService(this,
            R.raw.music
        )
        soundService!!.start()

        check_word_button.setOnClickListener {
            val similarCount = checkWord(str)
            if (similarCount == str.length) {
                finishGame()
            }
            if (similarCount != -1) {
                val item = ExampleItem(word_tocheck.text.toString(), similarCount.toString())
                wordList += item
                recycler_view.adapter?.notifyItemChanged(wordList.size - 1)
            }
        }

        give_up_button.setOnClickListener {
            finishGame()
            soundService!!.pause();
        }
    }

    private fun finishGame() {
        Toast.makeText(
            this, "Game is finished",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this, FinishGameActivity::class.java)
        //TODO add the values
        intent.putExtra(resources.getString(R.string.sourceWord),"")
        intent.putExtra(resources.getString(R.string.numberOfAttempts),"")
        intent.putExtra(resources.getString(R.string.gameResult),"")

        startActivity(intent)
        this.finish()

    }

    private fun generateWord(): String {
        //  TODO: generate word
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