package com.example.proyecto1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jugarButton: Button = findViewById(R.id.jugar_button)
        jugarButton.setOnClickListener {
            val intent = Intent(this, Activity3::class.java)
            startActivity(intent)
        }

        val opcionesButton: Button = findViewById(R.id.opciones_button)
        opcionesButton.setOnClickListener {
            val intent = Intent(this, OpcionesActivity::class.java)
            startActivity(intent)
        }

        val puntuacionesButton: Button = findViewById(R.id.puntuaciones_button)
        
        // El botón solo se habilita si se ha terminado al menos una partida
        puntuacionesButton.isEnabled = GameSettings.partidaTerminada
        puntuacionesButton.alpha = if (GameSettings.partidaTerminada) 1.0f else 0.45f
        
        puntuacionesButton.setOnClickListener {
            mostrarHistorial()
        }
    }

    private fun mostrarHistorial() {
        val historial = GameSettings.historialPartidas
        if (historial.isEmpty()) return

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Historial de Partidas")

        // Crear una lista de strings con el formato de cada partida
        val items = historial.map { partida ->
            "${partida.fecha} - ${partida.puntos} pts\n" +
            "Aciertos: ${partida.aciertos}/${partida.total} (${partida.dificultad})"
        }.toTypedArray()

        builder.setItems(items, null)
        builder.setPositiveButton("Cerrar", null)
        
        val dialog = builder.create()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        // Volvemos a verificar al regresar a la pantalla de inicio
        val puntuacionesButton: Button = findViewById(R.id.puntuaciones_button)
        puntuacionesButton.isEnabled = GameSettings.partidaTerminada
        puntuacionesButton.alpha = if (GameSettings.partidaTerminada) 1.0f else 0.45f
    }
}
