package com.mupel.clock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mupel.clock.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val themeMode: StateFlow<String> = settingsRepository.themeMode as StateFlow<String>
    val timeFormat24h: StateFlow<Boolean> = settingsRepository.timeFormat24h as StateFlow<Boolean>
    val vibrationEnabled: StateFlow<Boolean> = settingsRepository.vibrationEnabled as StateFlow<Boolean>
    val keepScreenAwake: StateFlow<Boolean> = settingsRepository.keepScreenAwake as StateFlow<Boolean>
    val selectedAlarmSound: StateFlow<String> = settingsRepository.selectedAlarmSound as StateFlow<String>

    fun setThemeMode(mode: String) {
        viewModelScope.launch {
            settingsRepository.setThemeMode(mode)
        }
    }

    fun setTimeFormat24h(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setTimeFormat24h(enabled)
        }
    }

    fun setVibrationEnabled(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setVibrationEnabled(enabled)
        }
    }

    fun setKeepScreenAwake(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setKeepScreenAwake(enabled)
        }
    }

    fun setSelectedAlarmSound(sound: String) {
        viewModelScope.launch {
            settingsRepository.setSelectedAlarmSound(sound)
        }
    }
}
