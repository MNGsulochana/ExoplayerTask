package com.hackathlon.exoplayertask.injection.module


import android.content.Context
import com.hackathlon.exoplayertask.injection.FireApp
import com.hackathlon.exoplayertask.injection.annotate.Appcontext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val fireApp: FireApp) {
    @Appcontext
    @Provides
    fun providesApplication(): FireApp {
        return fireApp
    }

    @Singleton
    @Appcontext
    @Provides
    fun providesAppContext(): Context = fireApp.applicationContext
}
