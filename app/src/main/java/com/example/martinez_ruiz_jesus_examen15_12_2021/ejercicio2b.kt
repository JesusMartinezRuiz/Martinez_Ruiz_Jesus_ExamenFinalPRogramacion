package com.example.martinez_ruiz_jesus_examen15_12_2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import com.google.android.material.textfield.TextInputEditText

class ejercicio2b : AppCompatActivity() {

    lateinit var swiNotif:Switch
    lateinit var swiDesactivar:Switch
    lateinit var cuentaAtras:TextInputEditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2b)

        swiNotif=findViewById(R.id.swi_notif)
        swiDesactivar=findViewById(R.id.swi_desactivar)
        cuentaAtras=findViewById(R.id.tiet_ej2Config_cuentaAtras)


        val app_id = getString(R.string.app_id)
        val sp_name = "${app_id}_SP_Examen"
        val SP = getSharedPreferences(sp_name,0)



        cuentaAtras.setText(SP.getInt(
            getString(R.string.SP_cuentaAtras),
            resources.getInteger(R.integer.SP_cuentaAtras_def)
        ).toString())


        swiNotif.isChecked= SP.getBoolean(
            getString(R.string.SP_swi_notif),
            resources.getBoolean(R.bool.SP_swi_notif_def)
        )

        swiDesactivar.isChecked=
            SP.getBoolean(
            getString(R.string.SP_desactivar),
            resources.getBoolean(R.bool.SP_desactivar_def)
        )





    }

    override fun onBackPressed() {
        super.onBackPressed()

        val app_id = getString(R.string.app_id)
        val sp_name = "${app_id}_SP_Examen"
        val SP = getSharedPreferences(sp_name,0)

        with(SP.edit()){
            putInt(
                getString(R.string.SP_cuentaAtras),
                cuentaAtras.text.toString().toInt()
            )

            putBoolean(
                getString(R.string.SP_desactivar),
                swiDesactivar.isChecked
            )

            putBoolean(
                getString(R.string.SP_swi_notif),
                swiNotif.isChecked
            )
            commit()
        }

    }


}