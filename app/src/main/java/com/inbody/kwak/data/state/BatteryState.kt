package com.inbody.kwak.data.state

import android.hardware.BatteryState
import kotlinx.coroutines.flow.StateFlow

data class BatteryStateData(
  // 내부 데이터를 어떻게 써야하는거지. 모델이나 멤버변수 정의하듯이 하는건가

)
class BatteryStateHolder {
  // 브로드캐스트에서 특정 이벤트 알람을 받고
  // 그에 대한 값을 컴포넌트에 반환
//  private val _batteryState = MutableStateFlow<BatteryState>(
//    value = TODO()
//  )

  val BatteryPct: StateFlow<Int> get() _batteryState
  var count by mutableIntStateOf(_batteryState)
    private set
  // 위 값에 따른 이벤트 발생 프로퍼티
}