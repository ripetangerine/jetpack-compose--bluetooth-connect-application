package com.inbody.kwak.data.state

import android.content.Context
import android.os.BatteryManager
import android.os.PowerManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.inbody.kwak.util.SystemBroadcastReceiver

data class Battery(
  val level: Int = -1, // TODO : 문법 맞는지 확인
  val scale: Int = 100,
  val status: Int = -1,
  val isCharging: Boolean = false,
  val isPowerSave: Boolean = false
  )

fun parseBattery(intent: Intent?): Battery?{ // 근데 여기서 왜 intent 를 받는거임..
  if(intent == null) return null
  val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
  val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) // intent에서 바로 뽑아온다던데 그건 왜 필요한거임?
  val scale = intent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
//  val status = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
  val batteryPct =
    if(level != null && scale != null) {
      (level * 100 / scale).toInt()
    } else {
      0
    }
  // label : powerManager 분리 혹은 위의 관련 intent 오류 고치기...아 powerManager 빼야할듯 여기서

  val status = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
  val isCharging =
    status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
  val isPowerSave = powerManager.isPowerSaveMode
}

@Composable
fun observeBatteryState(): Battery?{ // TODO: 리턴타입, 최소 인풋 추후 결정
  val context = LocalContext.current
  val battery = remember { mutableStateOf<Battery?>(null) }
  DisposableEffect(context) { // 생명 주기와 연관되었기,, 이외 rememver 처리 할 수도 잇음.
    val receiver = object: SystemBroadcastReceiver(){
      override fun onReceive(context: Context?, intent: Intent?){
        // TODO : intent에서 추출해서 Battery 로 반환
        battery.value = p
      }
    }
  }
}