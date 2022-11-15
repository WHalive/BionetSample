package com.example.bionetsample.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bionetsample.data.RegionItem
import com.example.bionetsample.data.SchoolItem
import com.example.bionetsample.data.SchoolTypeItem
import com.example.bionetsample.network.SignInApi
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private var _regions = MutableLiveData<List<RegionItem>>()
    var regions: LiveData<List<RegionItem>> = _regions
    private var _schoolTypes = MutableLiveData<List<SchoolTypeItem>>()
    val schoolTypes: LiveData<List<SchoolTypeItem>> = _schoolTypes
    private var _schools = MutableLiveData<List<SchoolItem>>()
    var schools: LiveData<List<SchoolItem>> = _schools

    init {
        getAllRegions()
    }

    fun getAllRegions() {
        viewModelScope.launch {
            try {
                _regions.value = SignInApi.retrofitService.getRegions().regions
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
                _schools.value =
                    SignInApi.retrofitService.getSchools(regionId, school).schools
            } catch (e: Exception) {
                Log.e("whalive", e.message.orEmpty())
                _schools.value = emptyList()
            }
        }
    }
}