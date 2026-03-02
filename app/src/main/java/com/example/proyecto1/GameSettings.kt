package com.example.proyecto1

object GameSettings {
    var numPreguntas: Int = 5
    var temas: List<String> = listOf("Historia", "Ciencia", "Deportes", "Arte", "Geografía")
    var dificultad: Int = 1 // 0: Fácil, 1: Normal, 2: Difícil
    var pistasEnabled: Boolean = true
    val PISTAS_INICIALES: Int = 3
}
