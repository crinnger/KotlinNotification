package com.example.kotlinnotificacao

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

lateinit var notificationChannel:NotificationChannel
lateinit var notificationManager:NotificationManager
lateinit var builder:NotificationCompat.Builder

fun Context.showNotification(channelId:String, title:String, body:String){
    Log.e("debug","showNotification")
    notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val intent = Intent(this,MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
        Log.e("debug","SDK ${Build.VERSION.SDK_INT}")
        notificationChannel = NotificationChannel(channelId,body, NotificationManager.IMPORTANCE_HIGH).apply {
            lightColor= Color.BLUE
            //enableVibration(true)
        }
        notificationManager.createNotificationChannel(notificationChannel)

        builder=NotificationCompat.Builder(this,channelId).apply {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentTitle(title)
            setContentText(body)
            setAutoCancel(true)
            setContentIntent(pendingIntent)
        }
    }
    Log.e("debug","notifica $channelId")
    notificationManager.notify(channelId.toInt(), builder.build())
}