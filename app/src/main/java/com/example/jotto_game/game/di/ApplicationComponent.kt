package com.example.jotto_game.game.di

import com.example.jotto_game.game.view.PlayingGameActivity
import com.example.jotto_game.start.view.MainActivity
import dagger.Component

@Component(modules = [ApplicationModule::class, SharedPrefsModule::class])
interface ApplicationComponent {

    fun inject(activity: PlayingGameActivity)

    fun inject(activity: MainActivity)
}