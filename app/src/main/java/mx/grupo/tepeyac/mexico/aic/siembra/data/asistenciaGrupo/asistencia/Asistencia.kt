package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
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
    val idTrabajador: String? = null,
    val editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idAsistencia: String? = null,
        entrada: Date,
        salida: Date?,
        idTrabajador: String? = null,
        editado: Boolean = false
    ) : this(id, idAsistencia, entrada, salida, idTrabajador, editado, false)
}