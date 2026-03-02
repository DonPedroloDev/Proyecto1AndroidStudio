package com.example.proyecto1

object QuestionBank {

    val allQuestions: List<Question> = listOf(

        // ─── HISTORIA ───────────────────────────────────────────
        Question(
            topic = "Historia",
            questionText = "¿Quién fue el líder de Alemania durante la Segunda Guerra Mundial?",
            correctAnswer = "Adolf Hitler",
            wrongAnswers = listOf("Napoleón Bonaparte", "Benito Mussolini", "Joseph Stalin")
        ),
        Question(
            topic = "Historia",
            questionText = "¿En qué año comenzó la Primera Guerra Mundial?",
            correctAnswer = "1914",
            wrongAnswers = listOf("1939", "1905", "1921")
        ),
        Question(
            topic = "Historia",
            questionText = "¿Qué imperio fue gobernado por Julio César?",
            correctAnswer = "Imperio Romano",
            wrongAnswers = listOf("Imperio Otomano", "Imperio Persa", "Imperio Bizantino")
        ),
        Question(
            topic = "Historia",
            questionText = "¿Qué país fue gobernado por los zares?",
            correctAnswer = "Rusia",
            wrongAnswers = listOf("Alemania", "Austria", "Francia")
        ),
        Question(
            topic = "Historia",
            questionText = "¿Qué civilización creó el calendario maya?",
            correctAnswer = "Los mayas",
            wrongAnswers = listOf("Los aztecas", "Los incas", "Los olmecas")
        ),

        // ─── CIENCIA ────────────────────────────────────────────
        Question(
            topic = "Ciencia",
            questionText = "¿Cuál es el planeta más grande del sistema solar?",
            correctAnswer = "Júpiter",
            wrongAnswers = listOf("Saturno", "Neptuno", "Tierra")
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Qué gas necesitan las plantas para realizar la fotosíntesis?",
            correctAnswer = "Dióxido de carbono",
            wrongAnswers = listOf("Oxígeno", "Nitrógeno", "Hidrógeno")
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Cuál es el órgano más grande del cuerpo humano?",
            correctAnswer = "La piel",
            wrongAnswers = listOf("El hígado", "El corazón", "El cerebro")
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Qué partícula tiene carga negativa?",
            correctAnswer = "Electrón",
            wrongAnswers = listOf("Protón", "Neutrón", "Núcleo")
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Qué científico propuso la teoría de la relatividad?",
            correctAnswer = "Albert Einstein",
            wrongAnswers = listOf("Isaac Newton", "Galileo Galilei", "Nikola Tesla")
        ),

        // ─── DEPORTES ───────────────────────────────────────────
        Question(
            topic = "Deportes",
            questionText = "¿Cuántos sets se necesitan para ganar un partido de tenis en Grand Slam (varonil)?",
            correctAnswer = "3 sets",
            wrongAnswers = listOf("2 sets", "4 sets", "5 sets")
        ),
        Question(
            topic = "Deportes",
            questionText = "¿En qué deporte destacó Michael Jordan?",
            correctAnswer = "Baloncesto",
            wrongAnswers = listOf("Béisbol", "Fútbol americano", "Tenis")
        ),
        Question(
            topic = "Deportes",
            questionText = "¿Cuánto dura un partido de fútbol profesional?",
            correctAnswer = "90 minutos",
            wrongAnswers = listOf("60 minutos", "120 minutos", "80 minutos")
        ),
        Question(
            topic = "Deportes",
            questionText = "¿Qué país es conocido por originar el sumo?",
            correctAnswer = "Japón",
            wrongAnswers = listOf("China", "Corea", "Tailandia")
        ),
        Question(
            topic = "Deportes",
            questionText = "¿Cuántos anillos olímpicos hay en la bandera olímpica?",
            correctAnswer = "5",
            wrongAnswers = listOf("4", "6", "7")
        ),

        // ─── ARTE ───────────────────────────────────────────────
        Question(
            topic = "Arte",
            questionText = "¿Quién pintó la Mona Lisa?",
            correctAnswer = "Leonardo da Vinci",
            wrongAnswers = listOf("Miguel Ángel", "Pablo Picasso", "Vincent van Gogh")
        ),
        Question(
            topic = "Arte",
            questionText = "¿Qué movimiento artístico pertenece Salvador Dalí?",
            correctAnswer = "Surrealismo",
            wrongAnswers = listOf("Impresionismo", "Cubismo", "Barroco")
        ),
        Question(
            topic = "Arte",
            questionText = "¿Qué instrumento tiene teclas, pedales y cuerdas?",
            correctAnswer = "Piano",
            wrongAnswers = listOf("Violín", "Guitarra", "Flauta")
        ),
        Question(
            topic = "Arte",
            questionText = "¿Quién es el autor de la obra 'El David'?",
            correctAnswer = "Miguel Ángel",
            wrongAnswers = listOf("Donatello", "Rafael", "Bernini")
        ),
        Question(
            topic = "Arte",
            questionText = "¿En qué país se encuentra el Museo del Louvre?",
            correctAnswer = "Francia",
            wrongAnswers = listOf("Italia", "España", "Alemania")
        ),

        // ─── GEOGRAFÍA ──────────────────────────────────────────
        Question(
            topic = "Geografía",
            questionText = "¿Cuál es el océano más grande del mundo?",
            correctAnswer = "Océano Pacífico",
            wrongAnswers = listOf("Océano Atlántico", "Océano Índico", "Océano Ártico")
        ),
        Question(
            topic = "Geografía",
            questionText = "¿Cuál es la capital de Canadá?",
            correctAnswer = "Ottawa",
            wrongAnswers = listOf("Toronto", "Vancouver", "Montreal")
        ),
        Question(
            topic = "Geografía",
            questionText = "¿En qué continente se encuentra Brasil?",
            correctAnswer = "América del Sur",
            wrongAnswers = listOf("África", "Europa", "Asia")
        ),
        Question(
            topic = "Geografía",
            questionText = "¿Cuál es el desierto más grande del mundo?",
            correctAnswer = "Antártico",
            wrongAnswers = listOf("Sahara", "Gobi", "Kalahari")
        ),
        Question(
            topic = "Geografía",
            questionText = "¿Qué país tiene forma de bota?",
            correctAnswer = "Italia",
            wrongAnswers = listOf("Grecia", "Portugal", "Chile")
        )
    )
}