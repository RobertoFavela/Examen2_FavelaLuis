package favela.luis.examen2_favelaluis

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AgregarCancion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.agregar_cancion)

        val etNombre: EditText = findViewById(R.id.etNombre)
        val etArtista: EditText = findViewById(R.id.etArtista)
        val etAlbum: EditText = findViewById(R.id.etAlbum)
        val etDuracion: EditText = findViewById(R.id.etDuracion)

        val btnAgregar: Button = findViewById(R.id.btnAgregarCancion)
        btnAgregar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val artista = etArtista.text.toString()
            val album = etAlbum.text.toString()
            val duracion = etDuracion.text.toString()

            if (nombre.isNotEmpty() && artista.isNotEmpty() && album.isNotEmpty() && duracion.isNotEmpty()) {
                val nuevaCancion = cancion(nombre, artista, album, duracion)

                val resultIntent = Intent()
                resultIntent.putExtra("nuevaCancion", nuevaCancion)
                setResult(RESULT_OK, resultIntent)
                finish()
            }  else {
                Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        val btnRgresar: Button = findViewById(R.id.btnRegresar)
        btnRgresar.setOnClickListener {
            finish()
        }
    }
}
