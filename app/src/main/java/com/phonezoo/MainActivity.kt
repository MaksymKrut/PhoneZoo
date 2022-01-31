package com.phonezoo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
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
                    Scaffold(topBar = { TopAppBarCompose() }) {
                        DeviceList(deviceList = deviceViewModel.deviceListResponse)
                        deviceViewModel.getDeviceList(applicationContext)
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBarCompose() {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(
                text = "BrowserStack Devices",
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(context, "Menu called", Toast.LENGTH_SHORT).show()
            }
            ) { Icon(Icons.Default.Menu, "Menu") }
        },
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Search called", Toast.LENGTH_SHORT).show()
            }
            ) { Icon(Icons.Default.Search, "Search") }
            IconButton(onClick = {
                Toast.makeText(context, "Favorite called", Toast.LENGTH_SHORT).show()
            }
            ) { Icon(Icons.Default.Favorite, "Favorite") }
        }
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