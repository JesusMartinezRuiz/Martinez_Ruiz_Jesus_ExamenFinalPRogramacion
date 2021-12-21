package com.example.martinez_ruiz_jesus_examen15_12_2021

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.concurrent.atomic.AtomicInteger

class ejercicio2 : AppCompatActivity() {


    lateinit var tvresuelve:TextView
    lateinit var boom:ImageView
    lateinit var desactivar:Button
    lateinit var resultado:TextInputEditText
    lateinit var calculo:TextView
    lateinit var iniciar:Button
    lateinit var cuentaAtras:TextView
    lateinit var ej2Config:Button
    lateinit var reslayout:TextInputLayout
    var contador = 0L
    var contadorSecs=0
    var operac=0
    lateinit var ct: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)


        ej2Config=findViewById(R.id.btn_ej2_config)
        ej2Config.setOnClickListener {
            val actividad = Intent(applicationContext,ejercicio2b::class.java)
            startActivity (actividad)
        }


    }

    override fun onStart() {
        super.onStart()

        val app_id = getString(R.string.app_id)
        val sp_name = "${app_id}_SP_Examen"
        val SP = getSharedPreferences(sp_name,0)

        val desactivarSP = SP.getBoolean(
            getString(R.string.SP_desactivar),
            resources.getBoolean(R.bool.SP_desactivar_def)
        )

        val notifSP = SP.getBoolean(
            getString(R.string.SP_swi_notif),
            resources.getBoolean(R.bool.SP_swi_notif_def)
        )

        cuentaAtras=findViewById(R.id.tv_num_cuentaAtras)
        iniciar=findViewById(R.id.btn_ej2_iniciar)
        calculo=findViewById(R.id.tv_ej2_calculo)
        resultado=findViewById(R.id.tiet_ej2_resultado)
        desactivar=findViewById(R.id.btn_ej2_desactivar)
        tvresuelve=findViewById(R.id.tv_desactivarResuelve)
        boom=findViewById(R.id.iv_boom)
        reslayout=findViewById(R.id.ej2ResultadoLayout)

        boom.isVisible=false



        desactivarOff()


        iniciar.setOnClickListener {

            if (desactivarSP==true && notifSP==false){//------------------Desactivar bomba

                desactivarOn()

                operac  = operacion()


                boom.isVisible=false

                contadorSecs=SP.getInt(
                    getString(R.string.SP_cuentaAtras),
                    resources.getInteger(R.integer.SP_cuentaAtras_def)
                )

                cuentaAtras.text = contadorSecs.toString()

                contador=SP.getInt(
                    getString(R.string.SP_cuentaAtras),
                    resources.getInteger(R.integer.SP_cuentaAtras_def)
                ).toLong()

                ct = object : CountDownTimer(contador*1000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {

                        contadorSecs--
                        cuentaAtras.text = contadorSecs.toString()


                    }

                    override fun onFinish() {
                        boom.isVisible=true

                    }
                }.start()

                desactivar.setOnClickListener {
                    if (operac==resultado.text.toString().toInt()){
                        ct.cancel()
                        desactivarOff()
                    }else{
                        ct.cancel()
                        boom.isVisible=true
                        desactivarOff()
                    }
                }




            }else if (notifSP==true && desactivarSP==false){//-------------------Notificaciones

                boom.isVisible=false

                contadorSecs=SP.getInt(
                    getString(R.string.SP_cuentaAtras),
                    resources.getInteger(R.integer.SP_cuentaAtras_def)
                )

                cuentaAtras.text = contadorSecs.toString()

                contador=SP.getInt(
                    getString(R.string.SP_cuentaAtras),
                    resources.getInteger(R.integer.SP_cuentaAtras_def)
                ).toLong()

                ct = object : CountDownTimer(contador*1000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {

                        contadorSecs--
                        cuentaAtras.text = contadorSecs.toString()


                    }

                    override fun onFinish() {
                        notificar()

                    }
                }.start()


            }else if(notifSP==true && desactivarSP==true){
                desactivarOn()

                operac  = operacion()


                boom.isVisible=false

                contadorSecs=SP.getInt(
                    getString(R.string.SP_cuentaAtras),
                    resources.getInteger(R.integer.SP_cuentaAtras_def)
                )

                cuentaAtras.text = contadorSecs.toString()

                contador=SP.getInt(
                    getString(R.string.SP_cuentaAtras),
                    resources.getInteger(R.integer.SP_cuentaAtras_def)
                ).toLong()

                ct = object : CountDownTimer(contador*1000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {

                        contadorSecs--
                        cuentaAtras.text = contadorSecs.toString()


                    }

                    override fun onFinish() {
                        notificar()

                    }
                }.start()

                desactivar.setOnClickListener {
                    if (operac==resultado.text.toString().toInt()){
                        ct.cancel()
                        desactivarOff()
                    }else{
                        ct.cancel()
                        notificar()
                        desactivarOff()
                    }
                }

            }else{//------------------------BOOM normal
                boom.isVisible=false

                contadorSecs=SP.getInt(
                    getString(R.string.SP_cuentaAtras),
                    resources.getInteger(R.integer.SP_cuentaAtras_def)
                )

                cuentaAtras.text = contadorSecs.toString()

                contador=SP.getInt(
                    getString(R.string.SP_cuentaAtras),
                    resources.getInteger(R.integer.SP_cuentaAtras_def)
                ).toLong()

                ct = object : CountDownTimer(contador*1000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {

                        contadorSecs--
                        cuentaAtras.text = contadorSecs.toString()


                    }

                    override fun onFinish() {
                        boom.isVisible=true
                        cuentaAtras.text = "0"

                    }
                }.start()
            }

        }

    }

    fun operacion():Int {
        var num1 = (0..10).random()
        var num2 = (0..10).random()

        var x=num1
        var y=num2

        if(num1<num2){
            num2=x
            num1=y
        }

        var opRandom = (0..2).random()
        var op=""
        var res=0

        if (opRandom==0){
            op="*"
            res=num1*num2
        }else if (opRandom==1){
            op="-"
            res=num1-num2
        }else{
            op="+"
            res=num1+num2
        }

        calculo.setText(num1.toString()+"$op"+num2.toString())

        return res
    }

    fun notificar() {

        val id = MainActivity.create_notification_id()

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val intent2 = Intent(this, ejercicio2::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent2 = PendingIntent.getActivity(this, 0, intent2, 0)

        val builder = NotificationCompat.Builder(this, MainActivity.CHANNEL_ID)
            .setSmallIcon(R.drawable.abstract_clouds)
            .setContentTitle("EXPLOTO LA BOMBA")
            .setContentText("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOO")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // el id debe de ser único para cada notificación y te servirá para hacer referencia
            // a dicha notificación en el futuro
            notify(id, builder.build())
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        ct.cancel()
    }

    fun desactivarOff(){
        tvresuelve.isVisible=false
        calculo.isVisible=false
        resultado.isVisible=false
        desactivar.isVisible=false
        reslayout.isVisible=false


        tvresuelve.isClickable=false
        calculo.isClickable=false
        resultado.isClickable=false
        desactivar.isClickable=false
        reslayout.isClickable=false
    }

    fun desactivarOn(){
        desactivar.isVisible=true
        tvresuelve.isClickable=true
        calculo.isClickable=true
        resultado.isClickable=true
        reslayout.isClickable=true

        desactivar.isClickable=true
        tvresuelve.isVisible=true
        calculo.isVisible=true
        resultado.isVisible=true
        reslayout.isVisible=true
    }




}