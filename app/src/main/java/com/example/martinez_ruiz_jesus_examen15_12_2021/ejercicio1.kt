package com.example.martinez_ruiz_jesus_examen15_12_2021

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ejercicio1 : AppCompatActivity() {

    data class planeta(val nombre:String,val tipo:String, val img:Int)

    lateinit var rv:RecyclerView
    lateinit var app_id :String
    lateinit var sp_name : String
    lateinit var SP : SharedPreferences
    companion object{
        var lista_planetas = mutableListOf<planeta>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)


    }

    override fun onStart() {
        super.onStart()
        app_id = getString(R.string.app_id)
        sp_name = "${app_id}_SP_ej1"
        SP = getSharedPreferences(sp_name,0)

        rv=findViewById(R.id.rv_ej1)

        val nombres = resources.getStringArray(R.array.ej1_planetas_nombres).toList()
        val tipo = resources.getStringArray(R.array.ej1_planetas_tipo).toList()
        val imagenes = resources.obtainTypedArray(R.array.ej1_planetas_img)

        nombres.forEachIndexed{ i, j ->
            lista_planetas.add(planeta(nombres[i],"("+tipo[i]+")",imagenes.getResourceId(i,R.drawable.abstract_clouds)))
        }

        actualizarAdap(lista_planetas)



    }

    fun actualizarAdap(lista : List<planeta>){
        rv.adapter = adaptador(lista_planetas,this)
        rv.layoutManager= LinearLayoutManager(this)
    }

}