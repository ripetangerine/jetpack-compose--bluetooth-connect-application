package com.inbody.kwak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.inbody.kwak.ui.SampleNavigationSuiteScaffoldParts
import com.inbody.kwak.ui.component.SootheBottomNavigation
import com.inbody.kwak.ui.component.CustomStatusBar
import com.inbody.kwak.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize().statusBarsPadding()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        CustomStatusBar(modifier = Modifier)
                        SampleNavigationSuiteScaffoldParts(
//                            modifier = Modifier.padding(innerPadding),
//                            modifier = Modifier.weight(1f), // 남은 공간 전부
                            navController = rememberNavController() // TODO : 이거 수정 필요
                        )
                    }
                }
            }
        }
    }
}

