package com.example.jotto_game.game.di

import android.content.Context
import com.example.jotto_game.GameApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideApplication() = GameApplication.APPLICATION

    @Provides
    fun provideContext(): Context = GameApplication.APPLICATION
}
