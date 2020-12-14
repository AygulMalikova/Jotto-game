package com.example.jotto_game.game.sharedpreferences

import android.content.SharedPreferences

fun SharedPreferences.change(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}
