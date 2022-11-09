package com.example.bionetsample.app

import android.app.Application
import com.example.bionetsample.database.BionetDatabase

class BionetApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        BionetDatabase.createInstance(this)
    }
}