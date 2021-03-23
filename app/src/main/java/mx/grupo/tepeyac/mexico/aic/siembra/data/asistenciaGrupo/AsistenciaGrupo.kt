package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class AsistenciaGrupo(
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_asistencia_grupo")
    val idAsistenciaGrupo: String? = null,
    @ColumnInfo(name = "id_grupo")
    val grupo: Long,
    @ColumnInfo(name = "id_rancho")
    val rancho: Long,
    val flete: Double?,
    val fecha: Date,
    val editado: Boolean = false,
    @Ignore
    val eliminado: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idAsistenciaGrupo: String? = null,
        grupo: Long,
        rancho: Long,
        flete: Double?,
        fecha: Date,
        editado: Boolean = false,
    ) : this(id, idAsistenciaGrupo, grupo, rancho, flete, fecha, editado, false)
}