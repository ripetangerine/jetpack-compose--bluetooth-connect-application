package com.inbody.kwak.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BluetoothConnected
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview


enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
    ) {
    SCANCONNECT("Scan&Connect", Icons.Default.BluetoothConnected, "Scan&Connect"),
    LOGGING("BLE Comm Log", Icons.Default.Settings, "BLE commnet Log")

}
// 위의 함수 추적
@Preview
@Composable
fun SampleNavigationSuiteScaffoldParts(){
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.SCANCONNECT) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            Icons.Default.Home, contentDescription = it.contentDescription
                        )
                    },
                    label = {Text(it.label)},
                    selected = it == currentDestination,
                    onClick = {currentDestination = it}
                )
            }
        }
    ){
        // destination content
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {}
    ){
        when(currentDestination){
            AppDestinations.SCANCONNECT -> ScanconnectDestination()
            AppDestinations.LOGGING -> LoggingDestination()
        }
    }
}

@Composable
fun ScanconnectDestination(){}
@Composable
fun LoggingDestination(){}


