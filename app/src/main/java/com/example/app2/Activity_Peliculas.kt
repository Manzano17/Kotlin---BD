package com.example.app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Activity_Peliculas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peliculas)

        val pt_titulo = findViewById<EditText>(R.id.edit_titulo)
        val sp_genero = findViewById<Spinner>(R.id.sp_genero)
        var genero = String()
        val btn_guardar = findViewById<Button>(R.id.btn_aguardarpeli)

        val lista_generos: Array<String> = resources.getStringArray(R.array.generosp)

        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista_generos)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_genero.adapter = adaptador

        sp_genero.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                genero = lista_generos[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        btn_guardar.setOnClickListener {
            val titulo = pt_titulo.text.toString()

            val genero = sp_genero.selectedItem.toString()

            val database = FirebaseDatabase.getInstance()

            val peliculasRef = database.getReference("peliculas")

            val peliculaId = peliculasRef.push().key

            val pelicula = Pelicula(titulo, genero)

            peliculaId?.let {
                peliculasRef.child(it).setValue(pelicula)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Se agrego pelicula ", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al aguardar pelicula", Toast.LENGTH_SHORT).show()
                        println("Error al aguardar la pelicula a FireBase: ${it.message}")
                    }
            }
            val intent = Intent(this, Activity_list_peliculas::class.java)
            startActivity(intent)
       }
    }
}