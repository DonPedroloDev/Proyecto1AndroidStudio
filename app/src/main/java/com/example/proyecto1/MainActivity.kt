package com.example.proyecto1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPlay = findViewById<Button>(R.id.btnPlay)
        val btnOptions = findViewById<Button>(R.id.btnOptions)

        btnPlay.setOnClickListener {
            viewModel.lastOpened = "Game"
            startActivity(Intent(this, GameActivity::class.java))
        }

        btnOptions.setOnClickListener {
            viewModel.lastOpened = "Options"
            startActivity(Intent(this, OptionsActivity::class.java))
        }
    }
}

class MainViewModel : ViewModel() {
    var lastOpened: String = ""
}