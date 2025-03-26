package favela.luis.examen2_favelaluis

import java.io.Serializable

data class cancion(
    val nombre: String,
    val artista: String,
    val album: String,
    val duracion: String
) : Serializable

