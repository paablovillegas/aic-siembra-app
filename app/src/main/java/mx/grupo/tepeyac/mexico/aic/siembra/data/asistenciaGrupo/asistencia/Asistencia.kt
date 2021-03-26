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
    var entrada: Date = Date(),
    var salida: Date? = null,
    @ColumnInfo(name = "id_trabajador")
    var idTrabajador: Long = 0L,
    @ColumnInfo(name = "id_asistencia_grupo")
    val idAsistenciaGrupo: Long,
    val editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idAsistencia: String? = null,
        entrada: Date = Date(),
        salida: Date? = null,
        idTrabajador: Long = 0L,
        idAsistenciaGrupo: Long,
        editado: Boolean = false
    ) : this(
        id,
        idAsistencia,
        entrada,
        salida,
        idTrabajador,
        idAsistenciaGrupo,
        editado,
        false
    )

    fun dataCorrect(): Boolean =
        idTrabajador > 0 && idAsistenciaGrupo > 0
}