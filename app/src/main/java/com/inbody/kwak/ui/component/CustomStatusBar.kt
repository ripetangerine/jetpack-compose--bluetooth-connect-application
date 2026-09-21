package com.inbody.kwak.ui.component

import android.R.attr.level
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.BatteryState
import android.os.BatteryManager
import android.os.PowerManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Battery0Bar
import androidx.compose.material.icons.filled.Battery1Bar
import androidx.compose.material.icons.filled.Battery2Bar
import androidx.compose.material.icons.filled.Battery3Bar
import androidx.compose.material.icons.filled.Battery4Bar
import androidx.compose.material.icons.filled.Battery5Bar
import androidx.compose.material.icons.filled.Battery6Bar
import androidx.compose.material.icons.filled.BatteryAlert
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.BatterySaver
import androidx.compose.material.icons.filled.BluetoothDisabled
import androidx.compose.material.icons.filled.NetworkWifi1Bar
import androidx.compose.material.icons.filled.NetworkWifi2Bar
import androidx.compose.material.icons.filled.NetworkWifi3Bar
import androidx.compose.material.icons.filled.SignalWifi0Bar
import androidx.compose.material.icons.filled.SignalWifi4Bar
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiOff
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getSystemService
import com.inbody.kwak.receiver.BatteryReceiver
import com.inbody.kwak.util.DateTimeDisplay
import com.inbody.kwak.util.SystemBroadcastReceiver

@Preview
@Composable
fun CustomStatusBar(
//    state: StatusBarState,
    modifier: Modifier = Modifier
) {
    var batteryPct by remember { mutableIntStateOf(0) }        // 잔량 %
    var isCharging by remember { mutableStateOf(false) }       // 충전 중인지
    var isPowerSave by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val wifiEnable = true // 나중에 값교체
    val wifiConnected = true
    val wifiStrength = 3 // 0~4

    // TODO : 이거 데이터 로직 분리하는게 좋을거같다.
     SystemBroadcastReceiver(systemAction = Intent.ACTION_BATTERY_CHANGED){ intent ->
         val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
         val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
         val status = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
         if(level != null && scale != null)
            batteryPct = (level * 100 / scale)
         isCharging =
             status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
    }
    SystemBroadcastReceiver(systemAction = PowerManager.ACTION_POWER_SAVE_MODE_CHANGED) {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        isPowerSave = powerManager.isPowerSaveMode
    }

    var batteryIcon =
        if(isCharging) {
            Icons.Default.BatteryChargingFull
        } else if (batteryPct <=15) {
            Icons.Default.BatteryAlert
        } else if (isPowerSave) {
            Icons.Default.BatterySaver
        } else if(batteryPct>=90) {
            Icons.Default.BatteryFull
        } else if (batteryPct >= 70) {
            Icons.Default.Battery6Bar
        } else if(batteryPct >= 50) {
            Icons.Default.Battery4Bar
        } else {
            Icons.Default.Battery2Bar
        }

    var batteryColor =
        if(isCharging){
            Color.Green
        } else if (batteryPct <= 15) {
            Color.Red
        } else if (isPowerSave) {
            Color(0xFFFF9800)
        } else {
            Color.Black
            }

    var wifiIcon =
        if(!wifiEnable){
            Icons.Default.WifiOff
        } else if(!wifiConnected){
            Icons.Default.SignalWifi0Bar
        } else if(wifiStrength == 4){
            Icons.Default.SignalWifi4Bar
        } else if(wifiStrength == 3){
            Icons.Default.NetworkWifi3Bar
        } else if(wifiStrength == 2){
            Icons.Default.NetworkWifi2Bar
        } else if(wifiStrength == 1){
            Icons.Default.NetworkWifi1Bar
        } else {
            Icons.Default.SignalWifi0Bar
        }



    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Cyan)
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){


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
            Icon(wifiIcon, contentDescription = null)
            Icon(batteryIcon, contentDescription = null, tint = batteryColor)
            Text("$batteryPct%")
        }
    }
}