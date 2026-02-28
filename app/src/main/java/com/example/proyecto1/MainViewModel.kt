package com.example.proyecto1

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.parcelize.Parcelize

// Anotación para que Kotlin genere automáticamente el código Parcelable
@Parcelize
data class GameOptions(
    val numPreguntas: Int = 5,
    val dificultad: String = "NORMAL",
    val conPistas: Boolean = true,
    val temas: List<Boolean> = listOf(true, true, true, true, true)
) : Parcelable

class MainViewModel : ViewModel() {

    private val _gameOptions = MutableLiveData<GameOptions>(GameOptions())
    val gameOptions: LiveData<GameOptions> = _gameOptions

    fun updateGameOptions(newOptions: GameOptions) {
        _gameOptions.value = newOptions
    }
}