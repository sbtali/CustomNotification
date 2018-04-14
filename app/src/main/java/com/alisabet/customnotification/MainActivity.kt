package com.alisabet.customnotification

import android.app.Notification
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi


class MainActivity : AppCompatActivity() {

    //1.Min sdk is 16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNotification()
    }

    private fun setupNotification(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannelId = "channel_id_01"

        val notificationBuilder = NotificationCompat.Builder(this, notificationChannelId)

        val notificationIntent = Intent(this, SecondActivity::class.java)
        val contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.notification_icon_background)
                .setTicker("Hearty365")
                .setContentTitle("Default notification")
                .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                .setContentInfo("Info")
                .setContentIntent(contentIntent)
        notificationManager.notify(1, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupNotificationForOREO(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannelId = "my_channel_id_01"

        val notificationChannel = NotificationChannel(notificationChannelId, "My Notifications", NotificationManager.IMPORTANCE_HIGH)

        notificationChannel.description = "Channel description"
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
        notificationChannel.enableVibration(true)
        notificationManager.createNotificationChannel(notificationChannel)
    }

}
