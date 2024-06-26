package com.example.ghusers.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mContext: Context, private val application: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return mContext
    }

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return application
    }

}