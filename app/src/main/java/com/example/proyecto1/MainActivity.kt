package com.example.proyecto1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var questionCounter: TextView
    private lateinit var currentQuestionText: TextView

    private var currentQuestion = 2
    private val totalQuestions = 7
    private lateinit var selectedQuestions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        questionCounter = findViewById(R.id.questionCounter)
        currentQuestionText = findViewById(R.id.currentQuestionText)

        selectedQuestions = selectQuestions(totalQuestions)
        updateQuestion()
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
    }
}