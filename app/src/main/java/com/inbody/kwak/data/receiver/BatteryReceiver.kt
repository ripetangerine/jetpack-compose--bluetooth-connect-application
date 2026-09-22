package com.inbody.kwak.data.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager

class BatteryReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BATTERY_CHANGED) {
            updateBatteryInfo(intent)
        }
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    }
}
fun updateBatteryInfo(intent: Intent){
    val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
    val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
    val batteryPct = level * 100 / scale
}

