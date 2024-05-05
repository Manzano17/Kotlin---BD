package com.example.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Activity_list_peliculas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_peliculas)

        val list_mostrarPeliculas = findViewById<ListView>(R.id.list_mostrarPeliculas)
        val listaPeliculas: ArrayList<String> = ArrayList()

        val database = FirebaseDatabase.getInstance()
        val peliculasRef = database.getReference("peliculas")

        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPeliculas)
        list_mostrarPeliculas.adapter = adaptador

        peliculasRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                adaptador.clear()
                for (registro in snapshot.children) {
                    val pelicula = registro.getValue(Pelicula::class.java)
                    val textoPelicula = "Titulo: "+pelicula?.titulo+", Género: "+pelicula?.genero
                    listaPeliculas.add(textoPelicula)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al consultar las peliculas ${error.message}")
            }
        })
    }
}