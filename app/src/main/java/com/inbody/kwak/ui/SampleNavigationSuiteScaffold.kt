package com.inbody.kwak.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BluetoothConnected
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.inbody.kwak.ui.screen.*


enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
    ) {
    HOME("Scan&Connect", Icons.Default.BluetoothConnected, "Scan&Connect"),
    LOGGING("BLE Log", Icons.Default.Settings, "BLE commnet Log")

}

@Composable
fun SampleNavigationSuiteScaffoldParts(modifier: Modifier = Modifier, navController: NavController){
//    val navController = rememberNavController()
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = backStackEntry?.destination?.route

    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }

    NavigationSuiteScaffold(
        modifier = modifier,
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon, contentDescription = it.contentDescription
                        )
                    },
                    label = {Text(it.label)},
                    selected = ( it == currentDestination ),
                    onClick = { currentDestination = it }
                )
            }
        }
    ){
//        NavHost(
//            navController = navController,
//            startDestination = "home"
//        ){
//            composable("home"){ ScanConScreen(navController) }
//            composable("logging") { BluLogScreen(navController) }
//        }
        when (currentDestination) {
            AppDestinations.HOME -> ScanConScreen(navController)
            AppDestinations.LOGGING -> BluLogScreen(navController)
        }
    }
}
