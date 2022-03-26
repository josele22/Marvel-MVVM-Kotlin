package com.josetorres.marvel

import android.app.Application
import android.content.Context

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
        val context: Context = applicationContext()
    }
}