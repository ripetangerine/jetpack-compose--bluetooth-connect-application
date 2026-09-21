package com.inbody.kwak.util

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DateTimeDisplay(modifier: Modifier = Modifier) {
    var curDateTimeText by remember {mutableStateOf("")}

    SystemBroadcastReceiver(systemAction = Intent.ACTION_TIME_TICK) { // 분에 따른 시스템 발송 브로드 캐스트
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        curDateTimeText = "${now.format(formatter)}";
    }

    Column(modifier=modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = curDateTimeText, color = Color.Black
        )
    }
}