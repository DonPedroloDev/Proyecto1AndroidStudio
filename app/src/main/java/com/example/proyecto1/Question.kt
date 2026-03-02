package com.example.proyecto1

data class Question(
    val topic: String,
    val questionText: String,
    val correctAnswer: String,
    val wrongAnswers: List<String>,
    val difficulty: Int = 1 // 1: Fácil, 2: Medio, 3: Difícil
)