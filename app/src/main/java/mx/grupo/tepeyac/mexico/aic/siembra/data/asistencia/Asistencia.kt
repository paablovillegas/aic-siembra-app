package mx.grupo.tepeyac.mexico.aic.siembra.data.asistencia

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Asistencia (
    @PrimaryKey
    var id_interno: Long = 0,
    var id_asistencia: String? = null,
    var id_trabajador: String? = null,
    var fecha_entrada: Date,
    var fecha_salida: Date? = null,
    var editado: Boolean = false
)