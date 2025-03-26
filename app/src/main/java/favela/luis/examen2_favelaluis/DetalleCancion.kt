package favela.luis.examen2_favelaluis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleCancion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalle_cancion)

        val cancion = intent.getSerializableExtra("cancion") as? cancion

        val tvNombre = findViewById<TextView>(R.id.tvNombre)
        val tvArtista = findViewById<TextView>(R.id.tvArtista)
        val tvAlbum = findViewById<TextView>(R.id.tvAlbum)
        val tvDuracion = findViewById<TextView>(R.id.tvDuracion)
        val btnPlay = findViewById<Button>(R.id.btnPlay)
        val btnStop = findViewById<Button>(R.id.btnStop)

        tvNombre.text = cancion?.nombre
        tvArtista.text = cancion?.artista
        tvAlbum.text = cancion?.album
        tvDuracion.text = cancion?.duracion

        btnPlay.text = "Play ${cancion?.nombre}"

        btnPlay.setOnClickListener {
            // esto ni va a hacer nada 🤙
        }

        btnStop.setOnClickListener {
            finish()
        }

        val BtnEliminar = findViewById<Button>(R.id.btnEliminar)
        BtnEliminar.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("cancionEliminada", cancion)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}
