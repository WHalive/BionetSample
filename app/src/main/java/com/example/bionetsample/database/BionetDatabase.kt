package com.example.bionetsample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bionetsample.dao.RegionDao
import com.example.bionetsample.dao.SchoolDao
import com.example.bionetsample.entity.RegionEntity
import com.example.bionetsample.entity.SchoolEntity

@Database(
    entities = [
        RegionEntity::class,
        SchoolEntity::class
    ],
    version = 1
)

abstract class BionetDatabase : RoomDatabase() {

    abstract fun regionDao(): RegionDao

    abstract fun schoolDao(): SchoolDao

    companion object {

        private var INSTANCE: BionetDatabase? = null

        fun createInstance(context: Context) {
            if (INSTANCE != null) return

            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    BionetDatabase::class.java,
                    "bionet_database"
                ).build()
            }
        }

        fun getInstance(): BionetDatabase {
            return INSTANCE!!
        }
    }
}