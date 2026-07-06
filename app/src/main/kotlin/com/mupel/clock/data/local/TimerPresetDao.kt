package com.mupel.clock.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mupel.clock.data.model.TimerPreset
import kotlinx.coroutines.flow.Flow

@Dao
interface TimerPresetDao {
    @Query("SELECT * FROM timer_presets ORDER BY duration DESC")
    fun getAllPresetsFlow(): Flow<List<TimerPreset>>

    @Query("SELECT * FROM timer_presets ORDER BY duration DESC")
    suspend fun getAllPresets(): List<TimerPreset>

    @Query("SELECT * FROM timer_presets WHERE id = :id")
    suspend fun getPresetById(id: Long): TimerPreset?

    @Insert
    suspend fun insertPreset(preset: TimerPreset): Long

    @Update
    suspend fun updatePreset(preset: TimerPreset)

    @Delete
    suspend fun deletePreset(preset: TimerPreset)

    @Query("DELETE FROM timer_presets WHERE id = :id")
    suspend fun deletePresetById(id: Long)
}
