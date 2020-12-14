package com.example.jotto_game

import android.app.Application
import com.example.jotto_game.game.di.DaggerApplicationComponent
//import com.example.jotto_game.game.di.ApplicationComponent

class GameApplication : Application() {

    val appComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this
    }

    companion object {
        lateinit var APPLICATION: Application
    }

}