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
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.random.Random

class PlayingGameActivity : AppCompatActivity() {
    private var soundService: BackgroundSoundService? = null
    var str = "-1";

    var finishGame = false

    @Inject lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.playing_game)

        (application as GameApplication).appComponent.inject(this)

        val level = intent.getStringExtra("difficulty").toString()
        val letters = intent.getStringExtra("letters").toString()

        var attempts = 0
        val wordList = ArrayList<ExampleItem>()

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(false)
        recycler_view.adapter = WordAdapter(wordList)

        soundService = BackgroundSoundService(this,
            R.raw.music
        )
        soundService!!.start()

        wordViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            println(words)
            words?.let { wordList.addAll(it) }
        })

        wordViewModel.secretWord.observe(this, Observer { lastWord ->
            if (finishGame) {
                str = ""
            } else {
                if (lastWord.isNotEmpty()) {
                    str = lastWord
                } else {
                    generateWord(level, letters)
                }
            }
        })

//        while (true) {
//            if (str != "-1") {
//                println(str)
//                break
//            }
//        }

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
    }

    private fun finishGame(word: String, attempts: Int, flag: Boolean) {
        Toast.makeText(
                this, "Game is finished",
                Toast.LENGTH_SHORT
        ).show()

        wordViewModel.saveSecretWord("")
        wordViewModel.resetDB()
        finishGame = true

        val intent = Intent(this, FinishGameActivity::class.java)
        intent.putExtra(resources.getString(R.string.sourceWord), word)
        intent.putExtra(resources.getString(R.string.numberOfAttempts), attempts)
        intent.putExtra(resources.getString(R.string.gameResult), flag)

        startActivity(intent)
        this.finish()

    }

    private fun generateWord(level: String, letters: String) {
        val number = letters.toInt()
        var low = 0.0
        var high = 10.0
        when (level) {
            "Easy" -> {
                low = 7.0
                high = 8.03
            }
            "Medium" -> {
                low = 4.0
                high = 7.0
            }
            else -> {
                low = 1.74
                high = 4.0
            }
        }
        fetchJson(number, low, high)
    }


    fun fetchJson(number: Int, low: Double, high: Double) {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://wordsapiv1.p.rapidapi.com/words/?letterPattern=%5E(%3F%3A(%5BA-Za-z%5D)(%3F!.*%5C1))*%24&pronunciationpattern=.*%C3%A6m%24&letters=${number}&limit=1000&page=1&frequencymin=${low}&frequencymax=${high}")
                .get()
                .addHeader("x-rapidapi-key", "4850f84b02mshe3f79bc0d6e5e39p1492cajsnae0a99006cbb")
                .addHeader("x-rapidapi-host", "wordsapiv1.p.rapidapi.com")
                .build()

        val response = client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                val result = JSONObject(body.toString())
                val json = result.getJSONObject("results")
                val words = json.getJSONArray("data")
                val word = words.get(Random.nextInt(0, words.length()))
                str = word.toString()

                wordViewModel.saveSecretWord(str)
            }

            override fun onFailure(call: Call, e: IOException) {

            }
        })
    }


    private fun checkWord(generated: String): Int {
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