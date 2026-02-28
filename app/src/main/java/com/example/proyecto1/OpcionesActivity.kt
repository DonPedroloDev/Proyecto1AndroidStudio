package com.example.proyecto1

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.proyecto1.databinding.ActivityOpcionesBinding

class OpcionesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpcionesBinding
    private lateinit var viewModel: OpcionesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Infla el layout usando View Binding, que es la forma moderna y segura de hacerlo.
        binding = ActivityOpcionesBinding.inflate(layoutInflater)
        // Establece la vista de la actividad a la raíz del layout inflado.
        setContentView(binding.root)

        // Inicializa el ViewModel, que sobrevivirá a cambios de configuración.
        viewModel = ViewModelProvider(this)[OpcionesViewModel::class.java]

        // Llama a las funciones para configurar los componentes de la UI.
        setupSlider()
        setupSpinner()
        setupObservers()
    }

    private fun setupSlider() {
        // Añade un listener al Slider. Se activará cada vez que el usuario mueva el control.
        binding.sliderPreguntas.addOnChangeListener { _, value, _ ->
            // Actualiza el valor en el ViewModel. Lo convertimos a Int porque el slider usa Floats.
            viewModel.setNumPreguntas(value.toInt())
        }
    }

    private fun setupSpinner() {
        // Crea el adaptador para el Spinner usando el array de strings que definimos en arrays.xml.
        ArrayAdapter.createFromResource(
            this,
            R.array.dificultad_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Define cómo se verá el menú desplegable.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Asigna el adaptador al Spinner en tu layout.
            binding.spinnerDificultad.adapter = adapter
        }
    }

    private fun setupObservers() {
        // Observa el LiveData 'numPreguntas' del ViewModel.
        // El código dentro de este bloque se ejecutará cada vez que el valor cambie.
        viewModel.numPreguntas.observe(this) { numPreguntas ->
            // Actualiza el texto del label para mostrar el número de preguntas seleccionado.
            binding.labelPreguntas.text = "Número de Preguntas: $numPreguntas"
            // Sincroniza la posición visual del slider con el dato del ViewModel.
            binding.sliderPreguntas.value = numPreguntas.toFloat()
        }
    }
}