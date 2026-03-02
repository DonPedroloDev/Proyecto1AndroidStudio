package com.example.proyecto1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jugarButton: Button = findViewById(R.id.jugar_button)
        jugarButton.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }

        val opcionesButton: Button = findViewById(R.id.opciones_button)
        opcionesButton.setOnClickListener {
            val intent = Intent(this, OpcionesActivity::class.java)
            startActivity(intent)
        }
    }
}
