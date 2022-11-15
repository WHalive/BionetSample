package com.example.bionetsample.app

import android.app.Application

class BionetApplication: Application() {
    override fun onCreate() {
        super.onCreate()
//        BionetDatabase.createInstance(this)
    }
}