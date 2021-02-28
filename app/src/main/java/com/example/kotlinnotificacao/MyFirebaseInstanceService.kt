package com.example.kotlinnotificacao

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseInstanceService : FirebaseMessagingService() {

    val tag = "firebaseMessagingService"

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.e(tag, FirebaseMessaging.getInstance().token.toString())
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e(tag, remoteMessage.from.toString())
        Log.e(tag, remoteMessage.notification?.title.toString())
        Log.e(tag, remoteMessage.notification?.body.toString())

        if(remoteMessage.notification!=null){
            this.showNotification("123",remoteMessage.notification?.title.toString(),remoteMessage.notification?.body.toString())
        }
    }
}