package com.example.proyecto1

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton

class Activity3 : AppCompatActivity() {

    private lateinit var questionCounter: TextView
    private lateinit var currentQuestionText: TextView
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button
    private lateinit var topicIcon: ImageView
    private lateinit var hintIndicator: TextView
    private lateinit var hintButton: Button
    private lateinit var hintUsedMark: TextView
    private lateinit var answerButtons: List<MaterialButton>

    private var currentQuestionIndex = 0
    private var totalQuestions = 0
    private lateinit var selectedQuestions: List<Question>

    private var pistasDisponibles = 3
    private var aciertosConsecutivos = 0

    private lateinit var questionsAnswers: MutableList<List<String>>
    private lateinit var questionsEliminated: MutableList<MutableSet<String>>
    private lateinit var questionsUsedHint: MutableList<Boolean>
    private lateinit var questionsAnswered: MutableList<Boolean>
    private lateinit var questionsUserSelection: MutableList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        loadGameData(savedInstanceState)
        
        setupListeners()
        updateUI()
    }

    private fun initViews() {
        questionCounter = findViewById(R.id.questionCounter)
        currentQuestionText = findViewById(R.id.currentQuestionText)
        prevButton = findViewById(R.id.prevButton)
        nextButton = findViewById(R.id.nextButton)
        topicIcon = findViewById(R.id.topicIcon)
        hintIndicator = findViewById(R.id.hintIndicator)
        hintButton = findViewById(R.id.hintButton)
        hintUsedMark = findViewById(R.id.hintUsedMark)
        
        answerButtons = listOf(
            findViewById(R.id.answerButton1),
            findViewById(R.id.answerButton2),
            findViewById(R.id.answerButton3),
            findViewById(R.id.answerButton4)
        )
    }

    private fun loadGameData(savedInstanceState: Bundle?) {
        pistasDisponibles = if (GameSettings.pistasEnabled) GameSettings.PISTAS_INICIALES else 0
        totalQuestions = GameSettings.numPreguntas

        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt("currentQuestionIndex")
            pistasDisponibles = savedInstanceState.getInt("pistasDisponibles")
            aciertosConsecutivos = savedInstanceState.getInt("aciertosConsecutivos")
            
            val indices = savedInstanceState.getIntArray("selectedQuestions") ?: intArrayOf()
            selectedQuestions = indices.map { QuestionBank.allQuestions[it] }
            
            val answersData = savedInstanceState.getStringArray("questionsAnswers") ?: arrayOf()
            questionsAnswers = answersData.map { it.split("|") }.toMutableList()
            
            val eliminatedData = savedInstanceState.getStringArray("questionsEliminated") ?: arrayOf()
            questionsEliminated = eliminatedData.map { if (it.isEmpty()) mutableSetOf<String>() else it.split("|").toMutableSet() }.toMutableList()
            
            questionsUsedHint = (savedInstanceState.getBooleanArray("questionsUsedHint") ?: booleanArrayOf()).toMutableList()
            questionsAnswered = (savedInstanceState.getBooleanArray("questionsAnswered") ?: booleanArrayOf()).toMutableList()
            questionsUserSelection = (savedInstanceState.getIntArray("questionsUserSelection") ?: intArrayOf()).toMutableList()
        } else {
            selectedQuestions = selectQuestions(totalQuestions, GameSettings.temas)
            if (selectedQuestions.isEmpty()) {
                Toast.makeText(this, "No hay preguntas para estos temas", Toast.LENGTH_LONG).show()
                finish()
                return
            }
            questionsAnswers = selectedQuestions.map { (it.wrongAnswers + it.correctAnswer).shuffled() }.toMutableList()
            questionsEliminated = MutableList(selectedQuestions.size) { mutableSetOf<String>() }
            questionsUsedHint = MutableList(selectedQuestions.size) { false }
            questionsAnswered = MutableList(selectedQuestions.size) { false }
            questionsUserSelection = MutableList(selectedQuestions.size) { -1 }
        }
    }

    private fun setupListeners() {
        prevButton.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                updateUI()
            }
        }

        nextButton.setOnClickListener {
            if (currentQuestionIndex < selectedQuestions.size - 1) {
                currentQuestionIndex++
                updateUI()
            } else {
                Toast.makeText(this, "¡Fin de la trivia!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        hintButton.setOnClickListener { usarPista() }

        answerButtons.forEachIndexed { i, btn ->
            btn.setOnClickListener { checkAnswer(i) }
        }
    }

    private fun updateUI() {
        if (selectedQuestions.isEmpty()) return
        
        val q = selectedQuestions[currentQuestionIndex]
        val answers = questionsAnswers[currentQuestionIndex]
        val eliminated = questionsEliminated[currentQuestionIndex]
        val answered = questionsAnswered[currentQuestionIndex]
        val userSel = questionsUserSelection[currentQuestionIndex]

        questionCounter.text = "${currentQuestionIndex + 1}/${selectedQuestions.size}"
        currentQuestionText.text = q.questionText
        hintIndicator.text = "Pistas: $pistasDisponibles"
        hintUsedMark.visibility = if (questionsUsedHint[currentQuestionIndex]) View.VISIBLE else View.GONE
        
        hintButton.isEnabled = !answered && pistasDisponibles > 0

        val iconRes = when(q.topic) {
            "Deportes" -> R.drawable.ball_icon
            "Historia" -> R.drawable.book_icon
            "Ciencia" -> R.drawable.flask_icon
            "Geografía" -> R.drawable.earth_icon
            "Arte" -> R.drawable.computer_icon
            else -> R.drawable.book_icon
        }
        topicIcon.setImageResource(iconRes)

        answerButtons.forEachIndexed { i, btn ->
            if (i < answers.size) {
                val text = answers[i]
                btn.text = text
                btn.visibility = if (eliminated.contains(text)) View.INVISIBLE else View.VISIBLE
                btn.isEnabled = !answered && !eliminated.contains(text)

                // Forzar colores para evitar problemas de visibilidad
                if (answered) {
                    if (text == q.correctAnswer) {
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#4CAF50")) // Verde
                        btn.setTextColor(Color.WHITE)
                        btn.visibility = View.VISIBLE
                    } else if (i == userSel) {
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F44336")) // Rojo
                        btn.setTextColor(Color.WHITE)
                    } else {
                        btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E0E0E0")) // Gris claro
                        btn.setTextColor(Color.GRAY)
                    }
                } else {
                    btn.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#6200EE")) // Morado original
                    btn.setTextColor(Color.WHITE)
                }
            } else {
                btn.visibility = View.GONE
            }
        }

        prevButton.isEnabled = currentQuestionIndex > 0
        nextButton.text = if (currentQuestionIndex == selectedQuestions.size - 1) "Finalizar" else "Siguiente"
    }

    private fun usarPista() {
        if (pistasDisponibles <= 0) return
        
        val q = selectedQuestions[currentQuestionIndex]
        val eliminated = questionsEliminated[currentQuestionIndex]
        val wrongAvailable = q.wrongAnswers.filter { !eliminated.contains(it) }

        if (wrongAvailable.isNotEmpty()) {
            pistasDisponibles--
            questionsUsedHint[currentQuestionIndex] = true
            
            val totalVisible = questionsAnswers[currentQuestionIndex].count { !eliminated.contains(it) }
            
            if (totalVisible <= 2) {
                // "Si solo quedan dos, la pista responderá la pregunta"
                eliminated.addAll(q.wrongAnswers)
                Toast.makeText(this, "Pista: Solo queda la correcta", Toast.LENGTH_SHORT).show()
            } else {
                // Eliminar una incorrecta al azar
                eliminated.add(wrongAvailable.shuffled().first())
                Toast.makeText(this, "Pista: Opción descartada", Toast.LENGTH_SHORT).show()
            }
            updateUI()
        }
    }

    private fun checkAnswer(i: Int) {
        if (questionsAnswered[currentQuestionIndex]) return

        questionsAnswered[currentQuestionIndex] = true
        questionsUserSelection[currentQuestionIndex] = i
        
        val isCorrect = questionsAnswers[currentQuestionIndex][i] == selectedQuestions[currentQuestionIndex].correctAnswer
        
        if (isCorrect) {
            Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show()
            if (!questionsUsedHint[currentQuestionIndex]) {
                aciertosConsecutivos++
                if (aciertosConsecutivos == 2) {
                    pistasDisponibles++
                    aciertosConsecutivos = 0
                    Toast.makeText(this, "¡Bonificación! +1 Pista", Toast.LENGTH_LONG).show()
                }
            } else {
                aciertosConsecutivos = 0
            }
        } else {
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show()
            aciertosConsecutivos = 0
        }
        updateUI()
    }

    private fun selectQuestions(num: Int, topics: List<String>): List<Question> {
        return QuestionBank.allQuestions
            .filter { it.topic in topics }
            .shuffled()
            .take(num)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentQuestionIndex", currentQuestionIndex)
        outState.putInt("pistasDisponibles", pistasDisponibles)
        outState.putInt("aciertosConsecutivos", aciertosConsecutivos)
        
        val indices = selectedQuestions.map { q -> QuestionBank.allQuestions.indexOfFirst { it.questionText == q.questionText } }.toIntArray()
        outState.putIntArray("selectedQuestions", indices)

        outState.putStringArray("questionsAnswers", questionsAnswers.map { it.joinToString("|") }.toTypedArray())
        outState.putStringArray("questionsEliminated", questionsEliminated.map { it.joinToString("|") }.toTypedArray())
        outState.putBooleanArray("questionsUsedHint", questionsUsedHint.toBooleanArray())
        outState.putBooleanArray("questionsAnswered", questionsAnswered.toBooleanArray())
        outState.putIntArray("questionsUserSelection", questionsUserSelection.toIntArray())
    }
}
