package com.example.proyecto1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto1.databinding.ActivityOpcionesBinding
import com.google.android.material.slider.Slider

class OpcionesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpcionesBinding
    private lateinit var viewModel: OpcionesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpcionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(OpcionesViewModel::class.java)

        setupSlider()
        setupSpinner()
        setupObservers()
    }

    private fun setupSlider() {
        binding.sliderPreguntas.addOnChangeListener { _, value, _ ->
            viewModel.setNumPreguntas(value.toInt())
        }
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.dificultad_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerDificultad.adapter = adapter
        }
    }

    private fun setupObservers() {
        viewModel.numPreguntas.observe(this) { numPreguntas ->
            binding.labelPreguntas.text = "Número de Preguntas: $numPreguntas"
            binding.sliderPreguntas.value = numPreguntas.toFloat()
        }
    }
}