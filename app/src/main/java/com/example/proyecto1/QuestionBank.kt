package com.example.proyecto1

object QuestionBank {

    val allQuestions: List<Question> = listOf(

        // ─── HISTORIA ───────────────────────────────────────────
        Question(
            topic = "Historia",
            questionText = "¿En qué año llegó Cristóbal Colón a América?",
            correctAnswer = "1492",
            wrongAnswers = listOf("1500", "1348", "1215")
        ),
        Question(
            topic = "Historia",
            questionText = "¿Quién fue el primer presidente de Estados Unidos?",
            correctAnswer = "George Washington",
            wrongAnswers = listOf("Abraham Lincoln", "Thomas Jefferson", "Benjamin Franklin")
        ),
        Question(
            topic = "Historia",
            questionText = "¿En qué país comenzó la Revolución Industrial?",
            correctAnswer = "Inglaterra",
            wrongAnswers = listOf("Francia", "Alemania", "Estados Unidos")
        ),
        Question(
            topic = "Historia",
            questionText = "¿Qué civilización construyó las pirámides de Giza?",
            correctAnswer = "Los egipcios",
            wrongAnswers = listOf("Los griegos", "Los romanos", "Los mayas")
        ),
        Question(
            topic = "Historia",
            questionText = "¿En qué año cayó el Muro de Berlín?",
            correctAnswer = "1989",
            wrongAnswers = listOf("1975", "1991", "1969")
        ),

        // ─── CIENCIA ────────────────────────────────────────────
        Question(
            topic = "Ciencia",
            questionText = "¿Cuál es el elemento más abundante en el universo?",
            correctAnswer = "Hidrógeno",
            wrongAnswers = listOf("Oxígeno", "Helio", "Carbono")
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿A qué velocidad viaja la luz en el vacío?",
            correctAnswer = "300,000 km/s",
            wrongAnswers = listOf("150,000 km/s", "500,000 km/s", "1,000,000 km/s")
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Cuántos huesos tiene el cuerpo humano adulto?",
            correctAnswer = "206",
            wrongAnswers = listOf("180", "230", "212")
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Qué planeta es conocido como el planeta rojo?",
            correctAnswer = "Marte",
            wrongAnswers = listOf("Júpiter", "Saturno", "Venus")
        ),
        Question(
            topic = "Ciencia",
            questionText = "¿Cuál es la fórmula química del agua?",
            correctAnswer = "H2O",
            wrongAnswers = listOf("CO2", "H2O2", "NaCl")
        ),

        // ─── GEOGRAFÍA ──────────────────────────────────────────
        Question(
            topic = "Geografía",
            questionText = "¿Cuál es el río más largo del mundo?",
            correctAnswer = "El Nilo",
            wrongAnswers = listOf("El Amazonas", "El Yangtsé", "El Mississippi")
        ),
        Question(
            topic = "Geografía",
            questionText = "¿Cuál es el país más grande del mundo?",
            correctAnswer = "Rusia",
            wrongAnswers = listOf("Canadá", "China", "Brasil")
        ),
        Question(
            topic = "Geografía",
            questionText = "¿En qué continente está Egipto?",
            correctAnswer = "África",
            wrongAnswers = listOf("Asia", "Europa", "Medio Oriente")
        ),
        Question(
            topic = "Geografía",
            questionText = "¿Cuál es la capital de Australia?",
            correctAnswer = "Canberra",
            wrongAnswers = listOf("Sídney", "Melbourne", "Brisbane")
        ),
        Question(
            topic = "Geografía",
            questionText = "¿Cuántos países tiene América del Sur?",
            correctAnswer = "12",
            wrongAnswers = listOf("10", "14", "8")
        ),

        // ─── DEPORTES ───────────────────────────────────────────
        Question(
            topic = "Deportes",
            questionText = "¿En qué país se inventó el fútbol?",
            correctAnswer = "Inglaterra",
            wrongAnswers = listOf("Brasil", "España", "Italia")
        ),
        Question(
            topic = "Deportes",
            questionText = "¿Cada cuántos años se celebra el Mundial de Fútbol?",
            correctAnswer = "4 años",
            wrongAnswers = listOf("2 años", "6 años", "3 años")
        ),
        Question(
            topic = "Deportes",
            questionText = "¿Cuántos jugadores hay en un equipo de baloncesto en cancha?",
            correctAnswer = "5",
            wrongAnswers = listOf("6", "4", "7")
        ),
        Question(
            topic = "Deportes",
            questionText = "¿En qué deporte se usa un birdie?",
            correctAnswer = "Golf",
            wrongAnswers = listOf("Tenis", "Bádminton", "Cricket")
        ),
        Question(
            topic = "Deportes",
            questionText = "¿Qué país ha ganado más veces el Mundial de Fútbol?",
            correctAnswer = "Brasil",
            wrongAnswers = listOf("Alemania", "Argentina", "Italia")
        ),

        // ─── TECNOLOGÍA ─────────────────────────────────────────
        Question(
            topic = "Tecnología",
            questionText = "¿Quién fundó Apple?",
            correctAnswer = "Steve Jobs",
            wrongAnswers = listOf("Bill Gates", "Elon Musk", "Mark Zuckerberg")
        ),
        Question(
            topic = "Tecnología",
            questionText = "¿Qué significa 'CPU'?",
            correctAnswer = "Central Processing Unit",
            wrongAnswers = listOf("Central Power Unit", "Computer Processing Unit", "Core Processing Utility")
        ),
        Question(
            topic = "Tecnología",
            questionText = "¿En qué año se lanzó el primer iPhone?",
            correctAnswer = "2007",
            wrongAnswers = listOf("2005", "2009", "2003")
        ),
        Question(
            topic = "Tecnología",
            questionText = "¿Qué lenguaje de programación creó Linus Torvalds?",
            correctAnswer = "Ninguno, creó Linux",
            wrongAnswers = listOf("Python", "C++", "Java")
        ),
        Question(
            topic = "Tecnología",
            questionText = "¿Qué significa 'HTTP'?",
            correctAnswer = "HyperText Transfer Protocol",
            wrongAnswers = listOf("High Transfer Text Protocol", "HyperText Transmission Process", "Host Transfer Technology Protocol")
        )
    )
}