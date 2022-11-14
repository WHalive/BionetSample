package com.example.bionetsample.repository

import androidx.lifecycle.LiveData
import com.example.bionetsample.dao.SchoolDao
import com.example.bionetsample.entity.SchoolEntity
import com.example.bionetsample.network.SignInApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SchoolRepository(private val schoolDao: SchoolDao) {

    fun getSchools(): LiveData<List<SchoolEntity>> = schoolDao.getAllSchools()

    suspend fun insertSchools(regionId: Int, school: Int) {
        withContext(Dispatchers.IO) {
            launch {
                try {
                    val schoolsEntities =
                        SignInApi.retrofitService.getSchools(regionId, school).schools
                    schoolDao.insertSchools(schoolsEntities)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
