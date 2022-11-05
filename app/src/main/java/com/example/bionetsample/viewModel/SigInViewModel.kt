package com.example.bionetsample.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bionetsample.data.RegionItem
import com.example.bionetsample.network.SignInApi
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private var _regions = MutableLiveData<List<RegionItem>>()
    val regions: LiveData<List<RegionItem>> = _regions

    init {
        getAllRegions()
    }

    private fun getAllRegions() {
        viewModelScope.launch {
            try {
                _regions.value = SignInApi.retrofitService.getRegions().regions
                Log.d("SignInViewModel", "getRegions: ${regions.value}")
            } catch (e: Exception) {
                Log.e("whalive", e.message.orEmpty())
                _regions.value = emptyList()
            }
        }
    }
}