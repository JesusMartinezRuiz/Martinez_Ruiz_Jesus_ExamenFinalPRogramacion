package com.example.martinez_ruiz_jesus_examen15_12_2021

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import java.lang.RuntimeException
import java.util.concurrent.atomic.AtomicInteger

lateinit var ej1:Button
lateinit var ej2:Button
lateinit var ej3:Button

class MainActivity : AppCompatActivity() {

    companion object{
        val APP_ID = "com.example.martinez_ruiz_jesus_examen15_12_2021"
        val CHANNEL_ID = "${APP_ID}_c1"
        var id = AtomicInteger(0)

        fun create_notification_id():Int{
            return id.incrementAndGet()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ej1=findViewById(R.id.main_ej1)
        ej2=findViewById(R.id.main_ej2)
        ej3=findViewById(R.id.main_ej3)


        createNotificationChannel()

    }

    fun changeActivity(v: View){
        val idActivity = when(v.id){
            R.id.main_ej1 -> ejercicio1::class.java
            R.id.main_ej2 -> ejercicio2::class.java
            R.id.main_ej3 -> ejercicio3::class.java
            else->{
                throw RuntimeException("No se encontro la actividad")
            }
        }
        val intent= Intent(this,idActivity)
        startActivity(intent)
    }

    fun createNotificationChannel() {
        // Creamos el canal de notificaciones si estamos en un movil con Android 8 o superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(MainActivity.CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}