package com.example.app2

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Activity_Canciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canciones)

        val pt_titulo = findViewById<EditText>(R.id.pt_titulo)
        val pt_cantante = findViewById<EditText>(R.id.pt_cantante)
        val btn_guardar = findViewById<Button>(R.id.btn_guardar)

        btn_guardar.setOnClickListener {
            val canciones = CancionesHelper(this, "cancionesbd", null, 1)
            val bd = canciones.writableDatabase
            val registro = ContentValues()
            registro.put("titulo", pt_titulo.text.toString())
            registro.put("cantante", pt_cantante.text.toString())
            bd.insert("cancion", null, registro)
            bd.close()
            pt_titulo.setText("")
            pt_cantante.setText("")
            Toast.makeText(this,"Se agrego la cancion: ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Activity_list_canciones::class.java)
            startActivity(intent)
        }
    }
}