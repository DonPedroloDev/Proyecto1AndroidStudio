package com.example.proyecto1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity3 : AppCompatActivity() {

    private lateinit var questionCounter: TextView
    private lateinit var currentQuestionText: TextView
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button
    private lateinit var topicIcon: ImageView

    private var currentQuestion = 1
    private var totalQuestions = 0 // Se inicializa desde GameSettings
    private lateinit var selectedQuestions: List<Question>

    // Variable lista para que tus compañeros usen la dificultad (0: Fácil, 1: Normal, 2: Difícil)
    private var gameDifficulty = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Inicialización de Vistas ---
        questionCounter = findViewById(R.id.questionCounter)
        currentQuestionText = findViewById(R.id.currentQuestionText)
        prevButton = findViewById(R.id.prevButton)
        nextButton = findViewById(R.id.nextButton)
        topicIcon = findViewById(R.id.topicIcon)

        // --- Cargar Opciones desde el Singleton GameSettings ---
        val requestedQuestions = GameSettings.numPreguntas
        val selectedTopics = GameSettings.temas
        gameDifficulty = GameSettings.dificultad

        if (savedInstanceState != null) {
            // Restaura el estado si la actividad se recrea (ej. rotación)
            currentQuestion = savedInstanceState.getInt("currentQuestion")
            val indices = savedInstanceState.getIntArray("selectedQuestions")!!
            selectedQuestions = indices.map { QuestionBank.allQuestions[it] }
            totalQuestions = selectedQuestions.size
        } else {
            // Inicia por primera vez: selecciona preguntas según las opciones
            currentQuestion = 1
            selectedQuestions = selectQuestions(requestedQuestions, selectedTopics)

            if (selectedQuestions.isEmpty()) {
                Toast.makeText(this, "No hay preguntas para los temas seleccionados.", Toast.LENGTH_LONG).show()
                finish()
                return
            }
            totalQuestions = selectedQuestions.size
        }

        updateQuestion()

        // --- Listeners de Botones (código existente) ---
        prevButton.setOnClickListener {
            if (currentQuestion > 1) {
                currentQuestion--
                updateQuestion()
            }
        }

        nextButton.setOnClickListener {
            if (currentQuestion < totalQuestions) {
                currentQuestion++
                updateQuestion()
            }
        }
    }

    private fun selectQuestions(questionsToSelect: Int, fromTopics: List<String>): List<Question> {
        // Si no se seleccionaron temas, no hay preguntas que devolver.
        if (fromTopics.isEmpty()) {
            return emptyList()
        }

        // 1. Filtrar las preguntas por los temas seleccionados (Tu aporte)
        val questionsByTopic = QuestionBank.allQuestions
            .filter { it.topic in fromTopics }
            .groupBy { it.topic }
            .mapValues { it.value.shuffled().toMutableList() } // Mezcla las preguntas dentro de cada tema

        // Si no hay preguntas para los temas seleccionados, devuelve una lista vacía.
        if (questionsByTopic.isEmpty()) {
            return emptyList()
        }

        // 2. Lógica de Round-Robin para repartir las preguntas (El aporte de tu compañero, corregido)
        val result = mutableListOf<Question>()
        val availableTopics = questionsByTopic.keys.toMutableList()
        var topicIndex = 0

        // Repetir hasta alcanzar el número de preguntas deseado o agotar todas las disponibles
        while (result.size < questionsToSelect && availableTopics.isNotEmpty()) {
            // Selecciona un tema de forma cíclica
            val currentTopic = availableTopics[topicIndex % availableTopics.size]
            val questionsForTopic = questionsByTopic[currentTopic]

            if (questionsForTopic != null && questionsForTopic.isNotEmpty()) {
                // Añade la primera pregunta de la lista y la elimina para no repetirla
                result.add(questionsForTopic.removeAt(0))
                topicIndex++ // Avanza al siguiente tema en la próxima iteración
            } else {
                // Si un tema se queda sin preguntas, se elimina de la lista para no volver a considerarlo
                availableTopics.remove(currentTopic)
            }
        }

        // 3. Mezcla el resultado final para que el orden no sea predecible (Tu aporte)
        return result.shuffled()
    }

    private fun updateQuestion() {
        if (selectedQuestions.isEmpty()) {
            currentQuestionText.text = "No hay preguntas disponibles."
            prevButton.isEnabled = false
            nextButton.isEnabled = false
            return
        }

        questionCounter.text = "$currentQuestion/$totalQuestions"
        currentQuestionText.text = selectedQuestions[currentQuestion - 1].questionText

        val question = selectedQuestions[currentQuestion - 1]
        val iconRes = when (question.topic) {
            "Deportes"    -> R.drawable.ball_icon
            "Historia"    -> R.drawable.book_icon
            "Ciencia"     -> R.drawable.flask_icon
            "Geografía"   -> R.drawable.earth_icon
            "Arte"        -> R.drawable.computer_icon
            else          -> R.drawable.book_icon
        }
        topicIcon.setImageResource(iconRes)

        prevButton.isEnabled = currentQuestion > 1
        nextButton.isEnabled = currentQuestion < totalQuestions
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::selectedQuestions.isInitialized && selectedQuestions.isNotEmpty()) {
            outState.putInt("currentQuestion", currentQuestion)
            val indices = selectedQuestions.mapNotNull { QuestionBank.allQuestions.indexOf(it).takeIf { it != -1 } }.toIntArray()
            outState.putIntArray("selectedQuestions", indices)
        }
    }
}