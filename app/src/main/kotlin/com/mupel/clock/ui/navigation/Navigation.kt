package com.mupel.clock.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mupel.clock.ui.screens.ClockScreen
import com.mupel.clock.ui.screens.TimerScreen
import com.mupel.clock.ui.screens.StopwatchScreen
import com.mupel.clock.ui.screens.SettingsScreen

@Composable
fun MupelNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = "clock"
    ) {
        composable("clock") {
            ClockScreen(navController = navController)
        }
        composable("timer") {
            TimerScreen(navController = navController)
        }
        composable("stopwatch") {
            StopwatchScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }
    }
}

object Routes {
    const val CLOCK = "clock"
    const val TIMER = "timer"
    const val STOPWATCH = "stopwatch"
    const val SETTINGS = "settings"
}
