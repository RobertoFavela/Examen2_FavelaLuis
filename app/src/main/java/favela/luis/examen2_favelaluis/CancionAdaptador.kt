package favela.luis.examen2_favelaluis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class CancionAdaptador (private val context: Context, private val canciones: List<cancion>) : BaseAdapter() {
    private val colores = listOf(
        R.color.GunMetal,
        R.color.Payne_Grey,
        R.color.Indian_Red,
        R.color.Coral
    )

    private val mapaColores = mutableMapOf<String, Int>()

    override fun getCount(): Int {
        return canciones.size
    }

    override fun getItem(position: Int): Any {
        return canciones[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.cancion, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val cancion = canciones[position]
        holder.tvNombre.text = cancion.nombre
        holder.tvArtista.text = cancion.artista

        if (!mapaColores.containsKey(cancion.nombre)) {
            mapaColores[cancion.nombre] = colores.random()
        }
        holder.layout.setBackgroundResource(mapaColores[cancion.nombre]!!)

        return view
    }

    // no dire que el mapa de colores fueun fiasgo, porque se intento, y un fiasgo habria sido no haberlo hecho
    // pero efectivamente no me salio como queria, o no le entendi, creo que es porque el adaptador se resetea, no se

    private class ViewHolder(view: View) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvArtista: TextView = view.findViewById(R.id.tvArtista)
        val layout: ConstraintLayout = view.findViewById(R.id.layout)
    }
}