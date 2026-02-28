package com.example.proyecto1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OpcionesViewModel : ViewModel() {

    private val _numPreguntas = MutableLiveData<Int>().apply {
        value = 5
    }
    val numPreguntas: LiveData<Int> = _numPreguntas

    fun setNumPreguntas(value: Int) {
        _numPreguntas.value = value
    }
}