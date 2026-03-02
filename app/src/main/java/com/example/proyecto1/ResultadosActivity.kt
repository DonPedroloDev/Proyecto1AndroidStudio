package com.example.proyecto1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        val totalScore = intent.getIntExtra("TOTAL_SCORE", 0)
        val correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)
        val hintsUsed = intent.getIntExtra("HINTS_USED", 0)
        val remainingHints = intent.getIntExtra("REMAINING_HINTS", 0)
        val bonusPoints = intent.getIntExtra("BONUS_POINTS", 0)

        val resultImage: ImageView = findViewById(R.id.resultImage)
        val scoreText: TextView = findViewById(R.id.scoreText)
        val pistasText: TextView = findViewById(R.id.pistasText)
        val detailText: TextView = findViewById(R.id.detailText)
        val backToMainButton: Button = findViewById(R.id.backToMainButton)

        scoreText.text = "Puntaje Total: $totalScore"
        pistasText.text = "Aciertos: $correctAnswers / $totalQuestions"
        
        detailText.text = """
            Desglose:
            - Pistas usadas: $hintsUsed
            - Pistas restantes: $remainingHints
            - Bonificación: $bonusPoints pts
        """.trimIndent()

        // Lógica de imagen personalizada (Diseño libre)
        // Criterio: Combinación de puntaje y eficiencia (pistas)
        val maxPosible = totalQuestions * 100 + (remainingHints + hintsUsed) * 200
        val ratio = if (maxPosible > 0) totalScore.toFloat() / maxPosible.toFloat() else 0f

        when {
            ratio >= 0.8 -> {
                // Genio: Alto puntaje y pocas pistas usadas
                resultImage.setImageResource(R.drawable.logo_quizmania)
            }
            ratio >= 0.5 -> {
                // Erudito: Buen desempeño
                resultImage.setImageResource(R.drawable.book_icon)
            }
            ratio >= 0.3 -> {
                // Explorador: Desempeño medio
                resultImage.setImageResource(R.drawable.earth_icon)
            }
            else -> {
                // Aprendiz: Necesita mejorar
                resultImage.setImageResource(R.drawable.flask_icon)
            }
        }

        backToMainButton.setOnClickListener {
            finish()
        }
    }
}
