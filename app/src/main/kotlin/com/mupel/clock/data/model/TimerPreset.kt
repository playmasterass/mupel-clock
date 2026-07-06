package com.mupel.clock.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timer_presets")
data class TimerPreset(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val duration: Long, // Duration in milliseconds
    val isDefault: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
