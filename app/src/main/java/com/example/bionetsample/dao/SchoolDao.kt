package com.example.bionetsample.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bionetsample.entity.RegionEntity
import com.example.bionetsample.entity.SchoolEntity

@Dao
interface SchoolDao {
    @Query("SELECT *FROM school_entity")
    fun getAllSchools(): LiveData<List<SchoolEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchools(schools: List<SchoolEntity>)
}
