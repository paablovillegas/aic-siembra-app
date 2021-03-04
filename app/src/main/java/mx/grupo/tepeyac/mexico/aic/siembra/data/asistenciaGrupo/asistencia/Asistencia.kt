package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Asistencia(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_asistencia")
    val idAsistencia: String? = null,
    val entrada: Date,
    val salida: Date? = null,
    @ColumnInfo(name = "id_trabajador")
    val idTrabajador: String,
    val editado: Boolean = false,
    @ColumnInfo(name = "id_trabajador")
    val trabajador: String? = null,
)