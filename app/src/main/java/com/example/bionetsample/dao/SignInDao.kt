package com.example.bionetsample.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bionetsample.entity.RegionEntity

@Dao
interface RegionDao {
    @Query("SELECT * FROM region_entity")
    fun getAllRegions(): LiveData<List<RegionEntity>>

    @Insert
        (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegions(regions: List<RegionEntity>)
}