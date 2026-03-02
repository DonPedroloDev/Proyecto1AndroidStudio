package com.example.proyecto1

object GameSettings {
    var numPreguntas: Int = 5
    var temas: List<String> = listOf("Historia", "Ciencia", "Deportes", "Arte", "Geografía")
    var dificultad: Int = 1 // 0: Fácil, 1: Normal, 2: Difícil
    var pistasEnabled: Boolean = true
    val PISTAS_INICIALES: Int = 3
    
    // Variable para controlar si el botón de puntuaciones debe habilitarse
    var partidaTerminada: Boolean = false
    
    // Lista para guardar el historial de partidas
    val historialPartidas = mutableListOf<PartidaResult>()

    data class PartidaResult(
        val fecha: String,
        val puntos: Int,
        val aciertos: Int,
        val total: Int,
        val dificultad: String
    )
}
