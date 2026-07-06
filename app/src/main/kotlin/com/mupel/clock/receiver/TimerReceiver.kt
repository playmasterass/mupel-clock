package com.mupel.clock.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TimerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Handle timer events
        when (intent?.action) {
            Intent.ACTION_TIME_TICK -> {
                // Timer tick event
            }
        }
    }
}
