package com.inbody.kwak.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
public fun SootheBottomNavigation(modifier: Modifier = Modifier){
    NavigationBar(
        modifier = modifier
    ) { 
        NavigationBarItem(
            icon = {
                Icon(imageVector=Icons.Default.Bluetooth, contentDescription=null)
            },
            label = {
                Text("Home")
            },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            },
            label = {
                Text("Log")
            },
            selected=false,
            onClick={}
        )
    }
}