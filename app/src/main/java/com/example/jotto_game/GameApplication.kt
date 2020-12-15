package com.example.jotto_game

import android.app.Application
import com.example.jotto_game.game.di.ApplicationComponent
import com.example.jotto_game.game.di.DaggerApplicationComponent

class GameApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.create()
        APPLICATION = this
    }

    companion object {
        lateinit var APPLICATION: Application
    }
}
