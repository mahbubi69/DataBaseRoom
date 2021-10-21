package com.example.databaseroom.notif

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.databaseroom.value.Value

class NotifAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotif()
    }

    private fun createNotif() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val builder1 =
                NotificationChannel(
                    Value.CHANEL_1,
                    "channel_1",
                    NotificationManager.IMPORTANCE_HIGH
                )
            builder1.description = "ini adalah channel 1"

            val builder2 =
                NotificationChannel(
                    Value.CHANEL_2,
                    "channel_2",
                    NotificationManager.IMPORTANCE_HIGH
                )
            builder1.description = "ini adalah channel 2"

            //manager
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(builder1)
            manager?.createNotificationChannel(builder2)
        }
    }
}
