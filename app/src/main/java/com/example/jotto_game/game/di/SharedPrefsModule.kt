package com.example.jotto_game.game.di

import android.content.Context
import com.example.jotto_game.game.sharedpreferences.SharedPreferencesWrapper
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SharedPrefsModule {

    @Provides
    @Named("notSecure")
    fun provideSharedPrefs(context: Context) = SharedPreferencesWrapper(context, false)

    @Provides
    @Named("secure")
    fun provideSecureSharedPrefs(context: Context) = SharedPreferencesWrapper(context, true)
}
