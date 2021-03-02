package mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Ciclo (
    @PrimaryKey
    private var id_interno: Long = 0,
    val id_ciclo: String? = null,
    val id_tabla: Long = 0,
    val ciclo: String,
    val fecha_inicio: Date,
    val fecha_siembra: Date? = null,
    val fecha_fin_siembra: Date? = null,
    val fecha_fin: Date? = null,
    val editado: Boolean = false
)