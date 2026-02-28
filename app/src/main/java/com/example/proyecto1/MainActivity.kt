package com.example.proyecto1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.opcionesButton.setOnClickListener {
            // Crea un Intent para abrir OpcionesActivity
            val intent = Intent(this, OpcionesActivity::class.java)
            startActivity(intent)
        }

        binding.jugarButton.setOnClickListener {
            // Crea un Intent para abrir Activity3 (la pantalla de juego)
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }
    }
}
