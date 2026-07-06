package com.mupel.clock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mupel.clock.ui.navigation.MupelNavHost
import com.mupel.clock.ui.theme.MupelClockTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MupelClockApp()
        }
    }
}

@Composable
fun MupelClockApp() {
    MupelClockTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { paddingValues ->
            MupelNavHost(
                navController = navController,
                paddingValues = paddingValues
            )
        }
    }
}
