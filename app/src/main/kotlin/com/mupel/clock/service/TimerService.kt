package com.mupel.clock.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.mupel.clock.MainActivity
import com.mupel.clock.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerService : Service() {

    private val binder = LocalBinder()
    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())
    private var timerJob: Job? = null
    private var remainingTime = 0L
    private var totalDuration = 0L
    private var isRunning = false

    companion object {
        private const val NOTIFICATION_ID = 1001
        private const val CHANNEL_ID = "timer_channel"
        const val ACTION_START = "com.mupel.clock.ACTION_START"
        const val ACTION_PAUSE = "com.mupel.clock.ACTION_PAUSE"
        const val ACTION_RESUME = "com.mupel.clock.ACTION_RESUME"
        const val ACTION_STOP = "com.mupel.clock.ACTION_STOP"
    }

    inner class LocalBinder : Binder() {
        fun getService(): TimerService = this@TimerService
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startTimer()
            ACTION_PAUSE -> pauseTimer()
            ACTION_RESUME -> resumeTimer()
            ACTION_STOP -> stopTimer()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder = binder

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Timer Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun updateNotification() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Timer")
            .setContentText(formatTime(remainingTime))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    fun setDuration(duration: Long) {
        remainingTime = duration
        totalDuration = duration
    }

    fun startTimer() {
        if (!isRunning && remainingTime > 0) {
            isRunning = true
            timerJob = serviceScope.launch {
                while (remainingTime > 0 && isRunning) {
                    delay(1000)
                    remainingTime -= 1000
                    updateNotification()
                }
                if (remainingTime <= 0) {
                    isRunning = false
                }
            }
            updateNotification()
        }
    }

    fun pauseTimer() {
        isRunning = false
        timerJob?.cancel()
    }

    fun resumeTimer() {
        startTimer()
    }

    fun stopTimer() {
        isRunning = false
        timerJob?.cancel()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    fun getRemainingTime(): Long = remainingTime

    fun isTimerRunning(): Boolean = isRunning

    private fun formatTime(millis: Long): String {
        val totalSeconds = millis / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return when {
            hours > 0 -> "%02d:%02d:%02d".format(hours, minutes, seconds)
            else -> "%02d:%02d".format(minutes, seconds)
        }
    }
}
