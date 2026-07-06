package com.mupel.clock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mupel.clock.data.repository.TimerPresetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val presetRepository: TimerPresetRepository
) : ViewModel() {

    private val _remainingTime = MutableStateFlow(0L)
    val remainingTime: StateFlow<Long> = _remainingTime.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private val _isPaused = MutableStateFlow(false)
    val isPaused: StateFlow<Boolean> = _isPaused.asStateFlow()

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress.asStateFlow()

    private val _totalDuration = MutableStateFlow(0L)
    val totalDuration: StateFlow<Long> = _totalDuration.asStateFlow()

    private var timerJob: kotlinx.coroutines.Job? = null

    fun setDuration(durationMs: Long) {
        if (!_isRunning.value) {
            _remainingTime.value = durationMs
            _totalDuration.value = durationMs
            _progress.value = 0f
        }
    }

    fun start() {
        if (_remainingTime.value > 0 && !_isRunning.value) {
            _isRunning.value = true
            _isPaused.value = false
            startTimer()
        }
    }

    fun pause() {
        if (_isRunning.value) {
            _isRunning.value = false
            _isPaused.value = true
            timerJob?.cancel()
        }
    }

    fun resume() {
        if (_isPaused.value && _remainingTime.value > 0) {
            _isRunning.value = true
            _isPaused.value = false
            startTimer()
        }
    }

    fun stop() {
        _isRunning.value = false
        _isPaused.value = false
        timerJob?.cancel()
        _remainingTime.value = 0L
        _progress.value = 0f
    }

    fun reset() {
        stop()
        _remainingTime.value = _totalDuration.value
        _progress.value = 0f
    }

    fun addMinute() {
        _remainingTime.value += 60000
        _totalDuration.value += 60000
    }

    fun add5Minutes() {
        _remainingTime.value += 300000
        _totalDuration.value += 300000
    }

    fun add10Minutes() {
        _remainingTime.value += 600000
        _totalDuration.value += 600000
    }

    private fun startTimer() {
        timerJob = viewModelScope.launch {
            while (_remainingTime.value > 0 && _isRunning.value) {
                delay(1000)
                _remainingTime.value -= 1000
                updateProgress()

                if (_remainingTime.value <= 0) {
                    _remainingTime.value = 0
                    _isRunning.value = false
                    // Trigger alarm callback
                }
            }
        }
    }

    private fun updateProgress() {
        if (_totalDuration.value > 0) {
            _progress.value = (_remainingTime.value.toFloat() / _totalDuration.value.toFloat())
        }
    }
}
