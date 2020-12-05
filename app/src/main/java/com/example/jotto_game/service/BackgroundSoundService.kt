package com.example.jotto_game.service

import android.content.Context
import android.media.MediaPlayer

class BackgroundSoundService(val ctx: Context, val music: Int) {
    private val mediaPlayer = MediaPlayer.create(ctx, music)

    fun start() {
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun destroy() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}