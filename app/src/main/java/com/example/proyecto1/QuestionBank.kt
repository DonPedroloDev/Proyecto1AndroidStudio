package com.example.proyecto1

object QuestionBank {

    val allQuestions: List<Question> = listOf(

        // ─── HISTORIA ───────────────────────────────────────────
        Question(
            topic = "Historia",
            questionText = "¿Quién fue el líder de Alemania durante la Segunda Guerra Mundial?",
            correctAnswer = "Adolf Hitler",
            wrongAnswers = listOf("Napoleón Bonaparte", "Benito Mussolini", "Joseph Stalin"),
            difficulty = 1
        ),
        Question(
            topic = "Historia",
            questionText = "¿En qué año comenzó la Primera Guerra Mundial?",
            correctAnswer = "1914",
            wrongAnswers = listOf("1939", "1905", "1921"),
            difficulty = 2
        ),
        Question(
            topic = "Historia",
            questionText = "¿Qué imperio fue gobernado por Julio César?",
            correctAnswer = "Imperio Romano",
            wrongAnswers = listOf("Imperio Otomano", "Imperio Persa", "Imperio Bizantino"),
            difficulty = 1
        ),
        Question(
            topic = "Historia",
            questionText = "¿Qué país fue gobernado por los zares?",
            correctAnswer = "Rusia",
            wrongAnswers = listOf("Alemania", "Austria", "Francia"),
            difficulty = 1
        ),
        Question(
            topic = "Historia",
            questionText = "¿Qué civilización creó el calendario maya?",
            correctAnswer = "Los mayas",
            wrongAnswers = listOf("Los aztecas", "Los incas", "Los olmecas"),
            difficulty = 2
        ),

        // ─── CIENCIA ────────────────────────────────────────────
        Question(
            topic = "Ciencia",
            questionText = "¿Cuál es el planeta más grande del sistema solar?",
            correctAnswer = "Júpiter",
            wrongAnswers = listOf("Saturno", "Neptuno", "Tierra"),
            difficulty = 1
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Qué gas necesitan las plantas para realizar la fotosíntesis?",
            correctAnswer = "Dióxido de carbono",
            wrongAnswers = listOf("Oxígeno", "Nitrógeno", "Hidrógeno"),
            difficulty = 1
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Cuál es el órgano más grande del cuerpo humano?",
            correctAnswer = "La piel",
            wrongAnswers = listOf("El hígado", "El corazón", "El cerebro"),
            difficulty = 2
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Qué partícula tiene carga negativa?",
            correctAnswer = "Electrón",
            wrongAnswers = listOf("Protón", "Neutrón", "Núcleo"),
            difficulty = 2
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Qué científico propuso la teoría de la relatividad?",
            correctAnswer = "Albert Einstein",
            wrongAnswers = listOf("Isaac Newton", "Galileo Galilei", "Nikola Tesla"),
            difficulty = 3
        ),

        // ─── DEPORTES ───────────────────────────────────────────
        Question(
            topic = "Deportes",
            questionText = "¿Cuántos sets se necesitan para ganar un partido de tenis en Grand Slam (varonil)?",
            correctAnswer = "3 sets",
            wrongAnswers = listOf("2 sets", "4 sets", "5 sets"),
            difficulty = 3
        ),
        Question(
            topic = "Deportes",
            questionText = "¿En qué deporte destacó Michael Jordan?",
            correctAnswer = "Baloncesto",
            wrongAnswers = listOf("Béisbol", "Fútbol americano", "Tenis"),
            difficulty = 1
        ),
        Question(
            topic = "Deportes",
            questionText = "¿Cuánto dura un partido de fútbol profesional?",
            correctAnswer = "90 minutos",
            wrongAnswers = listOf("60 minutos", "120 minutos", "80 minutos"),
            difficulty = 1
        ),
        Question(
            topic = "Deportes",
            questionText = "¿Qué país es conocido por originar el sumo?",
            correctAnswer = "Japón",
            wrongAnswers = listOf("China", "Corea", "Tailandia"),
            difficulty = 2
        ),
        Question(
            topic = "Deportes",
            questionText = "¿Cuántos anillos olímpicos hay en la bandera olímpica?",
            correctAnswer = "5",
            wrongAnswers = listOf("4", "6", "7"),
            difficulty = 2
        ),

        // ─── ARTE ───────────────────────────────────────────────
        Question(
            topic = "Arte",
            questionText = "¿Quién pintó la Mona Lisa?",
            correctAnswer = "Leonardo da Vinci",
            wrongAnswers = listOf("Miguel Ángel", "Pablo Picasso", "Vincent van Gogh"),
            difficulty = 1
        ),
        Question(
            topic = "Arte",
            questionText = "¿Qué movimiento artístico pertenece Salvador Dalí?",
            correctAnswer = "Surrealismo",
            wrongAnswers = listOf("Impresionismo", "Cubismo", "Barroco"),
            difficulty = 2
        ),
        Question(
            topic = "Arte",
            questionText = "¿Qué instrumento tiene teclas, pedales y cuerdas?",
            correctAnswer = "Piano",
            wrongAnswers = listOf("Violín", "Guitarra", "Flauta"),
            difficulty = 1
        ),
        Question(
            topic = "Arte",
            questionText = "¿Quién es el autor de la obra 'El David'?",
            correctAnswer = "Miguel Ángel",
            wrongAnswers = listOf("Donatello", "Rafael", "Bernini"),
            difficulty = 2
        ),
        Question(
            topic = "Arte",
            questionText = "¿En qué país se encuentra el Museo del Louvre?",
            correctAnswer = "Francia",
            wrongAnswers = listOf("Italia", "España", "Alemania"),
            difficulty = 1
        ),

        // ─── GEOGRAFÍA ──────────────────────────────────────────
        Question(
            topic = "Geografía",
            questionText = "¿Cuál es el océano más grande del mundo?",
            correctAnswer = "Océano Pacífico",
            wrongAnswers = listOf("Océano Atlántico", "Océano Índico", "Océano Ártico"),
            difficulty = 1
        ),
        Question(
            topic = "Geografía",
            questionText = "¿Cuál es la capital de Canadá?",
            correctAnswer = "Ottawa",
            wrongAnswers = listOf("Toronto", "Vancouver", "Montreal"),
            difficulty = 3
        ),
        Question(
            topic = "Geografía",
            questionText = "¿En qué continente se encuentra Brasil?",
            correctAnswer = "América del Sur",
            wrongAnswers = listOf("África", "Europa", "Asia"),
            difficulty = 1
        ),
        Question(
            topic = "Geografía",
            questionText = "¿Cuál es el desierto más grande del mundo?",
            correctAnswer = "Antártico",
            wrongAnswers = listOf("Sahara", "Gobi", "Kalahari"),
            difficulty = 3
        ),
        Question(
            topic = "Geografía",
            questionText = "¿Qué país tiene forma de bota?",
            correctAnswer = "Italia",
            wrongAnswers = listOf("Grecia", "Portugal", "Chile"),
            difficulty = 1
        )
    )
}