package com.example.jotto_game.game.service

import android.content.Context
import android.media.MediaPlayer

/** add cool music while playing activity is  launching.
 * Music can be turned out with a button in right-up screen corner
 */
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