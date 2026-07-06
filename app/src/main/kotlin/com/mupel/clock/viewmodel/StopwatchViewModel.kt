package com.mupel.clock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StopwatchViewModel : ViewModel() {

    private val _elapsedTime = MutableStateFlow(0L)
    val elapsedTime: StateFlow<Long> = _elapsedTime.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private val _lapTimes = MutableStateFlow<List<Long>>(emptyList())
    val lapTimes: StateFlow<List<Long>> = _lapTimes.asStateFlow()

    private var stopwatchJob: kotlinx.coroutines.Job? = null

    fun start() {
        if (!_isRunning.value) {
            _isRunning.value = true
            startStopwatch()
        }
    }

    fun pause() {
        if (_isRunning.value) {
            _isRunning.value = false
            stopwatchJob?.cancel()
        }
    }

    fun resume() {
        if (!_isRunning.value && _elapsedTime.value > 0) {
            _isRunning.value = true
            startStopwatch()
        }
    }

    fun reset() {
        _isRunning.value = false
        stopwatchJob?.cancel()
        _elapsedTime.value = 0L
        _lapTimes.value = emptyList()
    }

    fun recordLap() {
        if (_isRunning.value) {
            val currentLaps = _lapTimes.value.toMutableList()
            currentLaps.add(_elapsedTime.value)
            _lapTimes.value = currentLaps
        }
    }

    private fun startStopwatch() {
        stopwatchJob = viewModelScope.launch {
            while (_isRunning.value) {
                delay(10) // Update every 10ms for millisecond precision
                _elapsedTime.value += 10
            }
        }
    }

    fun formatTime(milliseconds: Long): String {
        val totalSeconds = milliseconds / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        val ms = milliseconds % 1000

        return if (hours > 0) {
            "%02d:%02d:%02d.%02d".format(hours, minutes, seconds, ms / 10)
        } else {
            "%02d:%02d.%02d".format(minutes, seconds, ms / 10)
        }
    }
}
