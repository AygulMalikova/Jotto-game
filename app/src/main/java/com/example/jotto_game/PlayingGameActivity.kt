package com.example.jotto_game

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jotto_game.adapters.WordAdapter
import com.example.jotto_game.data.ExampleItem
import kotlinx.android.synthetic.main.playing_game.*
import java.util.*
import kotlin.collections.ArrayList

class PlayingGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playing_game)
        val str = generateWord()
        val wordList = ArrayList<ExampleItem>()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(false)
        recycler_view.adapter = WordAdapter(wordList)


        check_word_button.setOnClickListener {
            val similarCount = checkWord(str)
            val item = ExampleItem(word_tocheck.text.toString(), similarCount.toString())
            wordList += item
            // TODO: adapter does not updates, hz why, will fix
            recycler_view.adapter?.notifyDataSetChanged()
        }
    }


    private fun generateWord(): String {
        //  TODO: generate word
        return "about"
    }


    private fun checkWord(generated: String): Int {
        // TODO: check if the word exists and does not contain repeated letters
        var word = word_tocheck.text.toString()
        if (word.isEmpty()) {
            Toast.makeText(
                this, "Please, enter the word first",
                Toast.LENGTH_SHORT
            ).show()
        } else if (word.length != generated.length) {
            Toast.makeText(
                this, "Please, enter the word with length:" +
                        generated.length.toString(), Toast.LENGTH_SHORT
            ).show()
        }
        word = word.toLowerCase(Locale.ROOT)

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