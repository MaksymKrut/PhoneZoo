package com.phonezoo.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.phonezoo.model.Device
import com.phonezoo.R


@Composable
fun DeviceItem(device: Device) {
    // TODO Add hamburger menu
    // TODO Add detail view, favourite toggle, favourite devices page, settings page
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Surface() {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = if (device.os.startsWith("ios")) R.drawable.ios else R.drawable.android,
                        builder = {
                            placeholder(coil.compose.base.R.drawable.notification_action_background)
                        }
                    ),
                    contentDescription = device.device,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxHeight()
                        .weight(0.2f)
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = device.device,
                        style = MaterialTheme.typography.subtitle2,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (device.realMobile) "Real Device" else "Virtual Device",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = when {
                            device.os.startsWith("ios") -> "iOS"
                            device.os.startsWith("android") -> "Android"
                            else -> {
                                device.os.replaceFirstChar { it.uppercase() }
                            }
                        } + " " + device.os_version,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }

            }
        }
    }

}