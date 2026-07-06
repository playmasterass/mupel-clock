package com.mupel.clock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ClockViewModel : ViewModel() {

    private val _currentTime = MutableStateFlow("00:00:00")
    val currentTime: StateFlow<String> = _currentTime.asStateFlow()

    private val _currentDate = MutableStateFlow("")
    val currentDate: StateFlow<String> = _currentDate.asStateFlow()

    private val _currentDay = MutableStateFlow("")
    val currentDay: StateFlow<String> = _currentDay.asStateFlow()

    private val _is24HourFormat = MutableStateFlow(false)
    val is24HourFormat: StateFlow<Boolean> = _is24HourFormat.asStateFlow()

    init {
        startClock()
    }

    private fun startClock() {
        viewModelScope.launch {
            while (true) {
                updateTime()
                kotlinx.coroutines.delay(1000)
            }
        }
    }

    private fun updateTime() {
        val now = LocalDateTime.now()
        
        // Time format
        val timeFormatter = if (_is24HourFormat.value) {
            DateTimeFormatter.ofPattern("HH:mm:ss")
        } else {
            DateTimeFormatter.ofPattern("hh:mm:ss a")
        }
        _currentTime.value = now.format(timeFormatter)

        // Date format
        val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        _currentDate.value = now.format(dateFormatter)

        // Day format
        val dayFormatter = DateTimeFormatter.ofPattern("EEEE")
        _currentDay.value = now.format(dayFormatter)
    }

    fun setTimeFormat24h(is24h: Boolean) {
        _is24HourFormat.value = is24h
        updateTime()
    }
}
