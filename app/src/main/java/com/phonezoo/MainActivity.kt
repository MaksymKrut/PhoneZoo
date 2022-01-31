package com.phonezoo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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
                Surface(color = MaterialTheme.colors.background) {
                    ScaffoldCompose()
                    DeviceList(deviceList = deviceViewModel.deviceListResponse)
                    deviceViewModel.getDeviceList(applicationContext)
                }
            }
        }
    }
}

@Composable
fun ScaffoldCompose() {
    Scaffold(topBar = { TopAppBarCompose() }) {}
}

@Composable
fun TopAppBarCompose() {
    TopAppBar(
        title = { Text(text = "Devices", fontSize = 20.sp) },
        navigationIcon = {},
        actions = {}
    )
}

@Composable
fun DeviceList(deviceList: List<Device>) {
    LazyColumn() {
        itemsIndexed(items = deviceList) { _, item ->
            DeviceItem(device = item)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhoneZooTheme {
        val device = Device("Default os", "Default os_version", "Default device name", true)
        DeviceItem(device = device)
    }
}