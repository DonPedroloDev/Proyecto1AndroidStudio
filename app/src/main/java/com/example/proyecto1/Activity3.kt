package com.example.proyecto1

import android.graphics.Color
import android.os.Bundle
import android.view.View
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

    private lateinit var answerButtons: List<Button>
    private lateinit var shuffledAnswers: Array<List<String>>
    private lateinit var selectedAnswerIndex: IntArray

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

        answerButtons = listOf(
            findViewById(R.id.answerButton1),
            findViewById(R.id.answerButton2),
            findViewById(R.id.answerButton3),
            findViewById(R.id.answerButton4)
        )

        val requestedQuestions = GameSettings.numPreguntas
        val selectedTopics = GameSettings.temas
        gameDifficulty = GameSettings.dificultad

        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt("currentQuestion")
            val indices = savedInstanceState.getIntArray("selectedQuestions")!!
            selectedQuestions = indices.map { QuestionBank.allQuestions[it] }
            totalQuestions = selectedQuestions.size

            selectedAnswerIndex = savedInstanceState.getIntArray("selectedAnswerIndex")!!
            val flat = savedInstanceState.getStringArrayList("shuffledAnswers")!!
            shuffledAnswers = Array(totalQuestions) { listOf() }
            var qi = 0
            val cur = mutableListOf<String>()
            for (item in flat) {
                if (item == "|END|") { shuffledAnswers[qi] = cur.toList(); cur.clear(); qi++ }
                else cur.add(item)
            }

        } else {
            currentQuestion = 1
            selectedQuestions = selectQuestions(requestedQuestions, selectedTopics)

            if (selectedQuestions.isEmpty()) {
                Toast.makeText(this, "No hay preguntas para los temas seleccionados.", Toast.LENGTH_LONG).show()
                finish()
                return
            }
            totalQuestions = selectedQuestions.size

            selectedAnswerIndex = IntArray(totalQuestions) { -1 }
            shuffledAnswers = Array(totalQuestions) { i -> buildAnswers(i) }
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
            "Deportes"   -> R.drawable.ball_icon
            "Historia"   -> R.drawable.book_icon
            "Ciencia"    -> R.drawable.flask_icon
            "Geografía"  -> R.drawable.earth_icon
            "Arte"       -> R.drawable.baseline_brush_24
            else         -> R.drawable.book_icon
        }
        topicIcon.setImageResource(iconRes)

        prevButton.isEnabled = currentQuestion > 1
        nextButton.isEnabled = currentQuestion < totalQuestions

        updateAnswerButtons()
    }

    private fun buildAnswers(questionIndex: Int): List<String> {
        val q = selectedQuestions[questionIndex]
        val numAnswers = when (gameDifficulty) {
            0    -> 2  // Fácil
            2    -> 4  // Difícil
            else -> 3  // Normal
        }
        val wrong = q.wrongAnswers.shuffled().take(numAnswers - 1)
        return (wrong + q.correctAnswer).shuffled()
    }

    private fun updateAnswerButtons() {
        val qi       = currentQuestion - 1
        val answers  = shuffledAnswers[qi]
        val selected = selectedAnswerIndex[qi]
        val correct  = selectedQuestions[qi].correctAnswer

        answerButtons.forEachIndexed { index, button ->
            if (index < answers.size) {
                button.visibility = View.VISIBLE
                button.text       = answers[index]
                button.isEnabled  = selected == -1

                button.setBackgroundColor(Color.LTGRAY)
                button.setTextColor(Color.BLACK)

                if (selected != -1) {
                    when {
                        answers[index] == correct -> button.setBackgroundColor(Color.GREEN)
                        index == selected         -> button.setBackgroundColor(Color.RED)
                    }
                }

                button.setOnClickListener {
                    selectedAnswerIndex[qi] = index
                    updateAnswerButtons()
                }
            } else {
                button.visibility = View.GONE
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::selectedQuestions.isInitialized && selectedQuestions.isNotEmpty()) {
            outState.putInt("currentQuestion", currentQuestion)
            val indices = selectedQuestions.mapNotNull {
                QuestionBank.allQuestions.indexOf(it).takeIf { it != -1 }
            }.toIntArray()
            outState.putIntArray("selectedQuestions", indices)

            outState.putIntArray("selectedAnswerIndex", selectedAnswerIndex)
            val flat = ArrayList<String>()
            shuffledAnswers.forEach { list -> flat.addAll(list); flat.add("|END|") }
            outState.putStringArrayList("shuffledAnswers", flat)
        }
    }
}