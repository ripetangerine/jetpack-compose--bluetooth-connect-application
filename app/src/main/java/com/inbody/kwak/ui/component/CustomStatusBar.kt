package com.inbody.kwak.ui.component

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery6Bar
import androidx.compose.material.icons.filled.BluetoothDisabled
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.inbody.kwak.receiver.BatteryReceiver
import com.inbody.kwak.util.DateTimeDisplay
import com.inbody.kwak.util.SystemBroadcastReceiver

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
    var batteryPct by remember { mutableIntStateOf(0) }

    // TODO : 이거 데이터 로직 분리하는게 좋을거같다.
    val batteryReceiver = SystemBroadcastReceiver(systemAction = Intent.ACTION_BATTERY_CHANGED){ intent ->
        val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        if(level != null && scale != null)
            batteryPct = (level * 100 / scale)
    }



    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Cyan)
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
//        Text("26.09.14 13:05:55")

        DateTimeDisplay(modifier = Modifier.align(Alignment.CenterVertically))
        // 날짜 실시간 갱신, 현재 시간이 매분 갱신

        // 배터리 잔량, %. 충전 상태 감지. 배터리 부족 15이하 빨간색, 절전 주황색, 충전 초록색 충전
        // 와이파이 상태, disabled-off/connected강도에 따른/disconnected-회색, 강도 0-4.
        // 블루투스 상태
        Row(
            // 디자인, 구조적 정렬
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(Icons.Default.BluetoothDisabled, contentDescription = null)
            Icon(Icons.Default.Wifi, contentDescription = null)
            Icon(Icons.Default.Battery6Bar, contentDescription = null)
            Text(batteryPct.toString())
        }
    }
}