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
import com.example.bionetsample.entity.SchoolEntity
import com.example.bionetsample.network.SignInApi
import com.example.bionetsample.repository.RegionRepository
import com.example.bionetsample.repository.SchoolRepository
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val repositoryRegion: RegionRepository
    private val repositorySchool: SchoolRepository
    private var _regions = MutableLiveData<List<RegionEntity>>(emptyList())
    var regions: LiveData<List<RegionEntity>> = _regions
    private var _schoolTypes = MutableLiveData<List<SchoolTypeItem>>()
    val schoolTypes: LiveData<List<SchoolTypeItem>> = _schoolTypes
    private var _schools = MutableLiveData<List<SchoolEntity>>()
    var schools: LiveData<List<SchoolEntity>> = _schools

    init {
        val bionetRegionDatabase = BionetDatabase.getInstance().regionDao()
        repositoryRegion = RegionRepository(bionetRegionDatabase)
        regions = repositoryRegion.getAllRegions()
        getAllRegions()

        val schoolDatabase = BionetDatabase.getInstance().schoolDao()
        repositorySchool = SchoolRepository(schoolDatabase)
        schools = repositorySchool.getSchools()
    }

    fun getAllRegions() {
        viewModelScope.launch {
            try {
//                _regions.value = SignInApi.retrofitService.getRegions().regions
                repositoryRegion.insertRegions()
                _schoolTypes.value
            } catch (e: Exception) {
                Log.e("whalive", "getRegions", e)
                _regions.value = emptyList()
            }
        }
    }

    fun getAllSchools(regionId: Int, school: Int) {
        viewModelScope.launch {
            try {
                repositorySchool.insertSchools(regionId, school)
//                _schools.value =
//                    SignInApi.retrofitService.getSchools(regionId, school).schools
                Log.d("viewModel", "$schools")
            } catch (e: Exception) {
                Log.e("whalive", e.message.orEmpty())
                _schools.value = emptyList()
            }
        }
    }
}