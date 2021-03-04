package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ActividadTrabajador(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_actividad_trabajador")
    val idActividadTrabajador: String? = null,
    @ColumnInfo(name = "id_actividad")
    val idActividad: String,
    @ColumnInfo(name = "id_trabajador")
    val idTrabajador: String,
    @ColumnInfo(name = "id_tabla")
    val idTabla: String,
    val fecha: Date,
    val editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
    @Ignore
    val type: TipoActividadTrabajador?,
) {
    constructor(
        id: Long = 0,
        idActividadTrabajador: String? = null,
        idActividad: String,
        idTrabajador: String,
        idTabla: String,
        fecha: Date,
        editado: Boolean = false,
    ) : this(
        id,
        idActividadTrabajador,
        idActividad,
        idTrabajador,
        idTabla,
        fecha,
        editado,
        false,
        null
    )
}

enum class TipoActividadTrabajador { REGULAR, EXTRA, BONO }