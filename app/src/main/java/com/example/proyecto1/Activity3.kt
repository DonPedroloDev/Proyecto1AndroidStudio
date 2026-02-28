package com.example.proyecto1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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
    private val totalQuestions = 10
    private lateinit var selectedQuestions: List<Question>


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

        if (savedInstanceState != null) {
            // Restaurar estado guardado
            currentQuestion = savedInstanceState.getInt("currentQuestion")
            val indices = savedInstanceState.getIntArray("selectedQuestions")!!
            selectedQuestions = indices.map { QuestionBank.allQuestions[it] }
        } else {
            // Primera vez que abre la Activity
            currentQuestion = 1
            selectedQuestions = selectQuestions(totalQuestions)
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

    private fun selectQuestions(total: Int): List<Question> {
        val questionsByTopic = QuestionBank.allQuestions.groupBy { it.topic }
        val topics = questionsByTopic.keys.toList()
        val result = mutableListOf<Question>()

        var topicIndex = 0
        val pickedPerTopic = mutableMapOf<String, MutableList<Question>>()

        // Inicializa listas vacías por tema y mezcla las preguntas de cada uno
        topics.forEach { topic ->
            pickedPerTopic[topic] = questionsByTopic[topic]!!.shuffled().toMutableList()
        }

        // Reparte las preguntas en round-robin entre los temas
        repeat(total) {
            val topic = topics[topicIndex % topics.size]
            val available = pickedPerTopic[topic]!!
            if (available.isNotEmpty()) {
                result.add(available.removeFirst())
            }
            topicIndex++
        }

        return result.shuffled() // mezcla el orden final
    }

    private fun updateQuestion() {
        questionCounter.text = "$currentQuestion/$totalQuestions"
        currentQuestionText.text = selectedQuestions[currentQuestion - 1].questionText  // ← índice 0

        // Cambiar icono según el tema
        val question = selectedQuestions[currentQuestion - 1]
        val iconRes = when (question.topic) {
            "Deportes"    -> R.drawable.ball_icon
            "Historia"    -> R.drawable.book_icon
            "Tecnología"  -> R.drawable.computer_icon
            "Geografía"   -> R.drawable.earth_icon
            "Ciencia"     -> R.drawable.flask_icon
            else          -> R.drawable.book_icon
        }
        topicIcon.setImageResource(iconRes)

        // Deshabilita los botones en los extremos
        prevButton.isEnabled = currentQuestion > 1
        nextButton.isEnabled = currentQuestion < totalQuestions
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentQuestion", currentQuestion)
        // Guardamos los índices de las preguntas seleccionadas
        val indices = selectedQuestions.map { QuestionBank.allQuestions.indexOf(it) }.toIntArray()
        outState.putIntArray("selectedQuestions", indices)
    }
}