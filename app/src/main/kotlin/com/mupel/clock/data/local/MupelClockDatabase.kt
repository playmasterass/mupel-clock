package com.mupel.clock.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mupel.clock.data.model.TimerPreset

@Database(
    entities = [TimerPreset::class],
    version = 1,
    exportSchema = false
)
abstract class MupelClockDatabase : RoomDatabase() {
    abstract fun timerPresetDao(): TimerPresetDao
}
