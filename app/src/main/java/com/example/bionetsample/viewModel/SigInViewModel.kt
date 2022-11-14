package com.example.bionetsample.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bionetsample.data.RegionItem
import com.example.bionetsample.data.SchoolItem
import com.example.bionetsample.data.SchoolTypeItem
import com.example.bionetsample.database.BionetDatabase
import com.example.bionetsample.entity.RegionEntity
import com.example.bionetsample.network.SignInApi
import com.example.bionetsample.repository.RegionRepository
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val repository: RegionRepository
    private var _regions = MutableLiveData<List<RegionEntity>>(emptyList())
    var regions: LiveData<List<RegionEntity>> = _regions
    private var _schoolTypes = MutableLiveData<List<SchoolTypeItem>>()
    val schoolTypes: LiveData<List<SchoolTypeItem>> = _schoolTypes
    private var _schools = MutableLiveData<List<SchoolItem>>()
    val schools: LiveData<List<SchoolItem>> = _schools

    init {
        val bionetDatabase = BionetDatabase.getInstance().regionDao()
        repository = RegionRepository(bionetDatabase)
        regions = repository.getAllRegions()
        getAllRegions()
    }

    fun getAllRegions() {
        viewModelScope.launch {
            try {
//                _regions.value = SignInApi.retrofitService.getRegions().regions
                repository.insertRegions()
                _schoolTypes.value
//                _schools.value = SignInApi.retrofitService.getSchools(regionId, schoolTypeIjtem).schools
            } catch (e: Exception) {
                Log.e("whalive", "getRegions", e)
                _regions.value = emptyList()
            }
        }
    }

    fun getAllSchools(regionId: Int, school: Int) {
        viewModelScope.launch {
            try {
                _schools.value =
                    SignInApi.retrofitService.getSchools(regionId, school).schools
            } catch (e: Exception) {
                Log.e("whalive", e.message.orEmpty())
                _regions.value = emptyList()
            }
        }
    }
}