package com.mupel.clock.di

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import androidx.room.Room
import com.mupel.clock.data.local.MupelClockDatabase
import com.mupel.clock.data.local.TimerPresetDao
import com.mupel.clock.data.prefs.SettingsPreferences
import com.mupel.clock.data.repository.SettingsRepository
import com.mupel.clock.data.repository.TimerPresetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MupelClockDatabase {
        return Room.databaseBuilder(
            context,
            MupelClockDatabase::class.java,
            "mupel_clock_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTimerPresetDao(database: MupelClockDatabase): TimerPresetDao {
        return database.timerPresetDao()
    }

    @Provides
    @Singleton
    fun provideSettingsPreferences(@ApplicationContext context: Context): SettingsPreferences {
        return SettingsPreferences(context)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(prefs: SettingsPreferences): SettingsRepository {
        return SettingsRepository(prefs)
    }

    @Provides
    @Singleton
    fun provideTimerPresetRepository(dao: TimerPresetDao): TimerPresetRepository {
        return TimerPresetRepository(dao)
    }

    @Provides
    @Singleton
    fun provideNotificationManager(@ApplicationContext context: Context): NotificationManager {
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @Provides
    @Singleton
    fun provideRingtoneManager(): RingtoneManager {
        return RingtoneManager(null)
    }
}
