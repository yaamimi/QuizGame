package com.example.quizapplication

import android.app.Application
import android.content.Context
import com.example.quizapplication.module.ApplicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class QuizzApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@QuizzApp)
            androidFileProperties()
            modules(ApplicationModule.applicationModule)
        }
    }
}