package com.mupel.clock.data.repository

import com.mupel.clock.data.prefs.SettingsPreferences
import kotlinx.coroutines.flow.Flow

class SettingsRepository(private val prefs: SettingsPreferences) {

    val themeMode: Flow<String> = prefs.themeMode
    val timeFormat24h: Flow<Boolean> = prefs.timeFormat24h
    val vibrationEnabled: Flow<Boolean> = prefs.vibrationEnabled
    val keepScreenAwake: Flow<Boolean> = prefs.keepScreenAwake
    val selectedAlarmSound: Flow<String> = prefs.selectedAlarmSound

    suspend fun setThemeMode(mode: String) {
        prefs.setThemeMode(mode)
    }

    suspend fun setTimeFormat24h(enabled: Boolean) {
        prefs.setTimeFormat24h(enabled)
    }

    suspend fun setVibrationEnabled(enabled: Boolean) {
        prefs.setVibrationEnabled(enabled)
    }

    suspend fun setKeepScreenAwake(enabled: Boolean) {
        prefs.setKeepScreenAwake(enabled)
    }

    suspend fun setSelectedAlarmSound(sound: String) {
        prefs.setSelectedAlarmSound(sound)
    }
}
