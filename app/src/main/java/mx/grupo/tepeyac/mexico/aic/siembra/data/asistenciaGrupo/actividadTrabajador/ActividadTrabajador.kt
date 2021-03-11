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
    val idActividad: Long,
    @ColumnInfo(name = "id_trabajador")
    val idTrabajador: Long,
    @ColumnInfo(name = "id_tabla")
    val idTabla: Long,
    @ColumnInfo(name = "id_asistencia_grupo")
    val idAsistenciaGrupo: Long,
    val fecha: Date,
    val editado: Boolean = false,
    val type: TipoActividadTrabajador?,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idActividadTrabajador: String? = null,
        idActividad: Long,
        idTrabajador: Long,
        idTabla: Long,
        idAsistenciaGrupo: Long,
        fecha: Date,
        editado: Boolean = false,
        type: TipoActividadTrabajador?,
    ) : this(
        id,
        idActividadTrabajador,
        idActividad,
        idTrabajador,
        idTabla,
        idAsistenciaGrupo,
        fecha,
        editado,
        type,
        false,
    )
}