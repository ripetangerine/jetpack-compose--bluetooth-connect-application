package com.inbody.kwak.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery6Bar
import androidx.compose.material.icons.filled.BluetoothDisabled
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class StatusBarState(
    val datetime: String,
    val bluetoothStatus: String,
    val internetStatus: String,
    val batteryStatus: String,
//    val batteryState // 몇퍼센트 ?
    )

@Preview
@Composable
fun StatusBar(
//    state: StatusBarState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp)
            .background(Color.Cyan).statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text("26.09.14 13:05:55")
        Row(
            // 디자인, 구조적 정렬
        ){
            Icon(Icons.Default.BluetoothDisabled, contentDescription = null)
            Icon(Icons.Default.Wifi, contentDescription = null)
            Icon(Icons.Default.Battery6Bar, contentDescription = null)
            Text(text = "89%")
        }
    }
}