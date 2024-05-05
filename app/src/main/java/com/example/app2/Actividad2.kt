package com.example.app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.TextView
import android.view.MenuItem
import android.widget.Button

class Actividad2 : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad2)

        val tv_bienvenida = findViewById<TextView>(R.id.tv_bienvenida)
        val nombreUsuario = intent.getStringExtra("usuario")
        val sharedPreferences = this.getSharedPreferences("MiPrimerSharedPreferences", MODE_PRIVATE)
        val apodo = sharedPreferences.getString("apodo", "error")
        tv_bienvenida.append(" " + apodo)
        val btn_agregarCancion = findViewById<Button>(R.id.btn_agregarcancion)
       val btn_AgregarPelicula = findViewById<Button>(R.id.btn_agregarpelicula)

       btn_agregarCancion.setOnClickListener {
           val intent = Intent(this, Activity_Canciones::class.java)
           startActivity(intent)
       }

       btn_AgregarPelicula.setOnClickListener {
           val intent = Intent(this, Activity_Peliculas::class.java)
           startActivity(intent)
       }
    }
}