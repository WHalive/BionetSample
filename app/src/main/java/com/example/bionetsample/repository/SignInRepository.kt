package com.example.bionetsample.repository

import androidx.lifecycle.LiveData
import com.example.bionetsample.dao.RegionDao
import com.example.bionetsample.entity.RegionEntity
import com.example.bionetsample.network.SignInApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegionRepository(private val regionDao: RegionDao) {

    fun getAllRegions(): LiveData<List<RegionEntity>> =
        regionDao.getAllRegions()

    suspend fun insertRegions() {
        withContext(Dispatchers.IO) {
            launch {
                try {
                    val regionsEntities = SignInApi.retrofitService.getRegions().regions
                    regionDao.insertRegions(regionsEntities)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}