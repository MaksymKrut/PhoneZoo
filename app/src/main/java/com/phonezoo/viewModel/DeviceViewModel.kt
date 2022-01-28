package com.phonezoo.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phonezoo.model.Device
import com.phonezoo.network.ApiService
import kotlinx.coroutines.launch
import java.lang.Exception

class DeviceViewModel : ViewModel() {
    var deviceListResponse: List<Device> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getDeviceList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val deviceList = apiService.getDevices()
                deviceListResponse = deviceList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}