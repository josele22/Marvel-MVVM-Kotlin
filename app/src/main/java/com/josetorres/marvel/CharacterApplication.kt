package com.josetorres.marvel

import android.app.Application
import android.content.Context
import com.josetorres.marvel.di.dataModule
import com.josetorres.marvel.di.domainModule
import com.josetorres.marvel.di.uiModule
import org.koin.core.context.startKoin

class CharacterApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: CharacterApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(dataModule, domainModule, uiModule)
        }
    }
}