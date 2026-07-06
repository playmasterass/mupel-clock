package com.mupel.clock.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "mupel_clock_settings")

class SettingsPreferences(private val context: Context) {

    companion object {
        private val THEME_MODE = stringPreferencesKey("theme_mode")
        private val TIME_FORMAT_24H = booleanPreferencesKey("time_format_24h")
        private val VIBRATION_ENABLED = booleanPreferencesKey("vibration_enabled")
        private val KEEP_SCREEN_AWAKE = booleanPreferencesKey("keep_screen_awake")
        private val SELECTED_ALARM_SOUND = stringPreferencesKey("selected_alarm_sound")
    }

    val themeMode: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[THEME_MODE] ?: "dark"
    }

    val timeFormat24h: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[TIME_FORMAT_24H] ?: false
    }

    val vibrationEnabled: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[VIBRATION_ENABLED] ?: true
    }

    val keepScreenAwake: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[KEEP_SCREEN_AWAKE] ?: false
    }

    val selectedAlarmSound: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[SELECTED_ALARM_SOUND] ?: "default"
    }

    suspend fun setThemeMode(mode: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_MODE] = mode
        }
    }

    suspend fun setTimeFormat24h(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[TIME_FORMAT_24H] = enabled
        }
    }

    suspend fun setVibrationEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[VIBRATION_ENABLED] = enabled
        }
    }

    suspend fun setKeepScreenAwake(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[KEEP_SCREEN_AWAKE] = enabled
        }
    }

    suspend fun setSelectedAlarmSound(sound: String) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_ALARM_SOUND] = sound
        }
    }
}

// Extension function for editing
private suspend inline fun <reified T> Context.editPreferences(block: suspend (androidx.datastore.preferences.core.MutablePreferences) -> Unit) {
    dataStore.edit(block)
}
