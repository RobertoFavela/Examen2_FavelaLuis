package favela.luis.examen2_favelaluis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var canciones: MutableList<cancion>
    private lateinit var adaptador: CancionAdaptador
    private lateinit var gridView: GridView
    private lateinit var btnAgregar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // pues le pondre mi top de canciones del spotify wrapped del 2024
        canciones = mutableListOf(
            cancion("Hell You Call a Dream", "The Warming", "Keep me feed", "2:56"), //mi cancion mas escuchada
            cancion("Automatic Sun","The Warming", "Keep me feed", "3:10"),
            cancion("S!CK","The Warming", "Keep me feed", "3:12"),
            cancion("Que Mas Quieres","The Warming", "Keep me feed", "3:04"),
            cancion("BurnOout","The Warming", "Keep me feed", "3:24"),
            cancion("DUMBAI", "CA7RIEL & Paco Amoroso", "PAPOTA", "2:41"), // escuche el tiny desk de ca7riel y paco profe, esta joya
            cancion("DISCIPLE", "The Warning", "Error", "3:40"),
            cancion("Black Holes", "The Warning", "Queen of the murder scene", "5:01"), // esta me la se en piano, y la lloro bien fuerte
            cancion("San Lucas","Kevin Kaarl", "San Lucas", "4:07"),

            ) // es que el anho pasado salio nuevo album de the warning, por eso las escuche tanto, pero igual esta buenisimo

        gridView = findViewById(R.id.gridView)
        btnAgregar = findViewById(R.id.btnAgregarCancion)

        adaptador = CancionAdaptador(this, canciones)
        gridView.adapter = adaptador

        btnAgregar.setOnClickListener {
            val intent = Intent(this, AgregarCancion::class.java)
            startActivityForResult(intent, REQUEST_CODE_AGREGAR_CANCION)
        }

        gridView.setOnItemClickListener { parent, view, position, id ->
            if (canciones.isNotEmpty() && position < canciones.size) {
                val cancion = canciones[position]
                val intent = Intent(this, DetalleCancion::class.java).apply {
                    putExtra("cancion", cancion)
                }
                startActivityForResult(intent, REQUEST_CODE_DETALLE_CANCION)
            } else {
                Toast.makeText(this, "No hay canciones disponibles", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_AGREGAR_CANCION && resultCode == RESULT_OK) {
            val nuevaCancion = data?.getSerializableExtra("nuevaCancion") as? cancion
            nuevaCancion?.let {
                canciones.add(it)
                adaptador.notifyDataSetChanged() // para que se actualice
            }
        } else if (requestCode == REQUEST_CODE_DETALLE_CANCION && resultCode == RESULT_OK) {
            val cancionEliminada = data?.getSerializableExtra("cancionEliminada") as? cancion
            cancionEliminada?.let {
                canciones.remove(it)
                adaptador.notifyDataSetChanged() // para que se actualice ootra vez
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_AGREGAR_CANCION = 1
        private const val REQUEST_CODE_DETALLE_CANCION = 2
    }
}
