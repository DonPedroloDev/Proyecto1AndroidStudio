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
    private var totalQuestions = 0
    private lateinit var selectedQuestions: List<Question>

    private var gameDifficulty = 0
    private var pistasDisponibles = 0
    private var aciertosConsecutivos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        questionCounter = findViewById(R.id.questionCounter)
        currentQuestionText = findViewById(R.id.currentQuestionText)
        prevButton = findViewById(R.id.prevButton)
        nextButton = findViewById(R.id.nextButton)
        topicIcon = findViewById(R.id.topicIcon)

        // --- Carga lógica de Pistas ---
        pistasDisponibles = if (GameSettings.pistasEnabled) GameSettings.PISTAS_INICIALES else 0
        gameDifficulty = GameSettings.dificultad
        totalQuestions = GameSettings.numPreguntas

        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt("currentQuestion")
            pistasDisponibles = savedInstanceState.getInt("pistasDisponibles")
            aciertosConsecutivos = savedInstanceState.getInt("aciertosConsecutivos")
            val indices = savedInstanceState.getIntArray("selectedQuestions")!!
            selectedQuestions = indices.map { QuestionBank.allQuestions[it] }
        } else {
            currentQuestion = 1
            selectedQuestions = selectQuestions(totalQuestions, GameSettings.temas)
            if (selectedQuestions.isEmpty()) {
                finish()
                return
            }
        }

        updateQuestion()

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

    /**
     * Lógica para que tus compañeros usen en la respuesta correcta.
     * Si acierta 2 veces seguidas sin pistas, gana una extra.
     */
    private fun registrarAcierto(usoPista: Boolean) {
        if (!usoPista) {
            aciertosConsecutivos++
            if (aciertosConsecutivos == 2) {
                pistasDisponibles++
                aciertosConsecutivos = 0
                Toast.makeText(this, "¡Bonificación! +1 Pista", Toast.LENGTH_SHORT).show()
            }
        } else {
            aciertosConsecutivos = 0 // Reinicia racha si usó pista
        }
    }

    /**
     * Función que tus compañeros deben llamar al pulsar el botón de Pista.
     * Devuelve el índice de una respuesta incorrecta para ocultar/marcar.
     */
    private fun usarPista(): Int? {
        if (pistasDisponibles > 0) {
            pistasDisponibles--
            // Aquí irá la lógica para identificar una respuesta incorrecta.
            // Se la dejo lista para que ellos la conecten con sus botones de respuesta.
            return 1 // Ejemplo de índice
        }
        return null
    }

    private fun selectQuestions(questionsToSelect: Int, fromTopics: List<String>): List<Question> {
        if (fromTopics.isEmpty()) return emptyList()

        val questionsByTopic = QuestionBank.allQuestions
            .filter { it.topic in fromTopics }
            .groupBy { it.topic }
            .mapValues { it.value.shuffled().toMutableList() }

        if (questionsByTopic.isEmpty()) return emptyList()

        val result = mutableListOf<Question>()
        val availableTopics = questionsByTopic.keys.toMutableList()
        var topicIndex = 0

        while (result.size < questionsToSelect && availableTopics.isNotEmpty()) {
            val currentTopic = availableTopics[topicIndex % availableTopics.size]
            val questionsForTopic = questionsByTopic[currentTopic]
            if (questionsForTopic != null && questionsForTopic.isNotEmpty()) {
                result.add(questionsForTopic.removeAt(0))
                topicIndex++
            } else {
                availableTopics.remove(currentTopic)
            }
        }
        return result.shuffled()
    }

    private fun updateQuestion() {
        questionCounter.text = "$currentQuestion/$totalQuestions"
        currentQuestionText.text = selectedQuestions[currentQuestion - 1].questionText

        val iconRes = when (selectedQuestions[currentQuestion - 1].topic) {
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
        outState.putInt("currentQuestion", currentQuestion)
        outState.putInt("pistasDisponibles", pistasDisponibles)
        outState.putInt("aciertosConsecutivos", aciertosConsecutivos)
        val indices = selectedQuestions.mapNotNull { QuestionBank.allQuestions.indexOf(it).takeIf { it != -1 } }.toIntArray()
        outState.putIntArray("selectedQuestions", indices)
    }
}

