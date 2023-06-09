package com.vivokey.vivokeyreader.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hoker.lib_utils.domain.ConnectionStatus
import com.vivokey.lib_bluetooth.domain.models.Host

@Composable
fun SelectedHostStatus(
    modifier: Modifier = Modifier,
    selectedHost: Host?,
    connectionStatus: ConnectionStatus,
    onCancel: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, bottom = 48.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                onCancel()
            }
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
        }
        Column {
            Text(
                text = selectedHost?.name ?: "Unknown",
                color = Color.DarkGray,
                fontSize = 24.sp
            )
            Text(
                text = selectedHost?.address ?: "",
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        }
        Box {
            selectedHost?.let {
                androidx.compose.animation.AnimatedVisibility(visible = connectionStatus == ConnectionStatus.DISCONNECTED) {
                    Column(
                        modifier = Modifier.padding(end = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Red)
                        )
                        Text(
                            text = "Disconnected",
                            color = Color.DarkGray,
                            fontSize = 12.sp
                        )
                    }
                }
                androidx.compose.animation.AnimatedVisibility(visible = connectionStatus == ConnectionStatus.CONNECTED) {
                    Column(
                        modifier = Modifier.padding(end = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Green)
                        )
                        Text(
                            text = "Connected",
                            color = Color.DarkGray,
                            fontSize = 12.sp
                        )
                    }
                }
                androidx.compose.animation.AnimatedVisibility(visible = connectionStatus == ConnectionStatus.CONNECTING) {
                    Column(
                        modifier = Modifier.padding(end = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.size(16.dp)
                        ) {
                            CircularProgressIndicator(
                                color = Color.DarkGray
                            )
                        }
                        Text(
                            text = "Connecting",
                            color = Color.DarkGray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}