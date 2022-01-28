package com.phonezoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.phonezoo.model.Device
import com.phonezoo.ui.theme.PhoneZooTheme
import com.phonezoo.view.DeviceItem
import com.phonezoo.viewModel.DeviceViewModel

class MainActivity : ComponentActivity() {

    val deviceViewModel by viewModels<DeviceViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneZooTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DeviceList(deviceList = deviceViewModel.deviceListResponse)
                    deviceViewModel.getDeviceList()
                }
            }
        }
    }
}

@Composable
fun DeviceList(deviceList: List<Device>) {
    LazyColumn() {
        itemsIndexed(items = deviceList) { index, item ->
            DeviceItem(device = item)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhoneZooTheme {
        val device = Device("Default name", "", "Default description", "Default category")
        DeviceItem(device = device)
    }
}