package com.xjkwak.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager =
                getSystemService(
                        Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(
                "com.xjkwak.notification.news",
                "Novedades",
                "Ultimas novedades")

        buttonNotify.setOnClickListener { sendNotification() }
    }

    private fun createNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        notificationManager?.createNotificationChannel(channel)
    }

    fun sendNotification() {

        val notificationID = 101

        val channelID = "com.xjkwak.notification.news"

        val notification = Notification.Builder(this,
                channelID)
                .setContentTitle("Ejemplo de Notification!")
                .setContentText("Hola Mundo!")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(channelID)
                .build()

        notificationManager?.notify(notificationID, notification)
    }
}