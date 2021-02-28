package com.example.kotlinnotificacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    lateinit var buttonNotification:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonNotification=findViewById(R.id.button_send_notification)
        buttonNotification.setOnClickListener{
            Log.e("debug","Onclick")
            this.showNotification("123","BootCamp Android Kotlin","Curso Kotlin")
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("***newtoken**", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
                        Log.e("***newtoken**", token.toString())

        })
    }
}