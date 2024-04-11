package com.example.app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSiguiente = findViewById<Button>(R.id.btn_siguiente)
        val ptUsuario = findViewById<EditText>(R.id.pt_usuario)
        val ptContrasenia = findViewById<EditText>(R.id.pt_contrasenia)
        val ptApodo = findViewById<EditText>(R.id.nuevoApodo2)

        btnSiguiente.setOnClickListener{
            val usuario = ptUsuario.text.toString()
            val contrasenia = ptContrasenia.text.toString()
            val apodo = ptApodo.text.toString()

            if (usuario == "antonio"){
                if (contrasenia == "123456"){
                    val intent = Intent(this, Actividad2::class.java)
                    intent.putExtra("usuario", usuario)
                    val sharedPreferences = this.getSharedPreferences("MiPrimerSharedPreferences", MODE_PRIVATE)
                    with(sharedPreferences.edit()){
                        putString("apodo", apodo)
                        apply()
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
            }

        }

    }
}