package com.mupel.clock.data.repository

import com.mupel.clock.data.local.TimerPresetDao
import com.mupel.clock.data.model.TimerPreset
import kotlinx.coroutines.flow.Flow

class TimerPresetRepository(private val dao: TimerPresetDao) {

    fun getAllPresetsFlow(): Flow<List<TimerPreset>> = dao.getAllPresetsFlow()

    suspend fun getAllPresets(): List<TimerPreset> = dao.getAllPresets()

    suspend fun getPresetById(id: Long): TimerPreset? = dao.getPresetById(id)

    suspend fun insertPreset(preset: TimerPreset): Long = dao.insertPreset(preset)

    suspend fun updatePreset(preset: TimerPreset) = dao.updatePreset(preset)

    suspend fun deletePreset(preset: TimerPreset) = dao.deletePreset(preset)

    suspend fun deletePresetById(id: Long) = dao.deletePresetById(id)
}
