package com.example.martinez_ruiz_jesus_examen15_12_2021

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adaptador (val lista:MutableList<ejercicio1.planeta>, val c : Context): RecyclerView.Adapter<adaptador.ViewHolder>()  {
    class ViewHolder(v : View): RecyclerView.ViewHolder(v){
        //var con elementos de la interfaz del rv
        var nombre: TextView
        var tipo: TextView
        var imagen : ImageView
        var borrar:ImageView
        //lateinit var borrar:ImageView

        init {
            //inicializamos los elementos de la interfaz
            nombre = v.findViewById(R.id.tv_nombrePlaneta)
            tipo= v.findViewById(R.id.tv_tipoPlaneta)
            imagen = v.findViewById(R.id.iv_fotoPlaneta)
            borrar=v.findViewById(R.id.iv_borrar)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(/*Cambia esto por la interfaz del rv ->*/R.layout.ventana_recycler, parent, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: adaptador.ViewHolder, position: Int) {
        //pones los elementos en la interfaz :)
        val p = lista[position]
        holder.nombre.text=p.nombre
        holder.tipo.text=p.tipo
        holder.imagen.setImageResource(p.img)
        holder.borrar.setOnClickListener{
            lista.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,lista.size)

        }

    }

    override fun getItemCount(): Int {
        return lista.size
    }



}

