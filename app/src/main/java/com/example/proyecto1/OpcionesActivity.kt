package com.example.proyecto1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto1.databinding.ActivityOpcionesBinding

class OpcionesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpcionesBinding
    private lateinit var viewModel: OpcionesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpcionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[OpcionesViewModel::class.java]

        setupSlider()
        setupSpinner()
        setupObservers()
        setupSaveButton()

        // Carga la configuración actual desde el Singleton
        loadOptionsFromSingleton()
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

    private fun setupSaveButton() {
        binding.buttonSave.setOnClickListener {
            saveOptionsToSingleton()
            Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveOptionsToSingleton() {
        // Guardar temas seleccionados
        val selectedTemas = mutableListOf<String>()
        if (binding.checkboxTema1.isChecked) selectedTemas.add("Historia")
        if (binding.checkboxTema2.isChecked) selectedTemas.add("Ciencia")
        if (binding.checkboxTema3.isChecked) selectedTemas.add("Deportes")
        if (binding.checkboxTema4.isChecked) selectedTemas.add("Arte")
        if (binding.checkboxTema5.isChecked) selectedTemas.add("Geografía")
        GameSettings.temas = selectedTemas

        // Guardar otras opciones
        GameSettings.numPreguntas = binding.sliderPreguntas.value.toInt()
        GameSettings.dificultad = binding.spinnerDificultad.selectedItemPosition
        GameSettings.pistasEnabled = binding.switchPistas.isChecked
    }

    private fun loadOptionsFromSingleton() {
        // Cargar y mostrar temas
        binding.checkboxTema1.isChecked = "Historia" in GameSettings.temas
        binding.checkboxTema2.isChecked = "Ciencia" in GameSettings.temas
        binding.checkboxTema3.isChecked = "Deportes" in GameSettings.temas
        binding.checkboxTema4.isChecked = "Arte" in GameSettings.temas
        binding.checkboxTema5.isChecked = "Geografía" in GameSettings.temas

        // Cargar y mostrar otras opciones
        viewModel.setNumPreguntas(GameSettings.numPreguntas)
        binding.spinnerDificultad.setSelection(GameSettings.dificultad)
        binding.switchPistas.isChecked = GameSettings.pistasEnabled
    }
}