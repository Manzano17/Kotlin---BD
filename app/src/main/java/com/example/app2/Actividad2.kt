package com.example.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Actividad2 : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad2)

        val tv_bienvenida = findViewById<TextView>(R.id.tv_bienvenida)
        val nombreUsuario = intent.getStringExtra("usuario")
        val sharedPreferences = this.getSharedPreferences("MiPrimerSharedPreferences", MODE_PRIVATE)
        val apodo = sharedPreferences.getString("apodo", "error")
        tv_bienvenida.append(" " + apodo)
    }
}