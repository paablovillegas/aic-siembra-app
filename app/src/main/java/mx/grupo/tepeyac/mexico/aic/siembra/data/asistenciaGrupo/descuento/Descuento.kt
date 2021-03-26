package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Descuento(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_descuento")
    val idDescuento: String? = null,
    var total: Double = 0.0,
    var motivo: String = "",
    val fecha: Date = Date(),
    @ColumnInfo(name = "id_trabajador")
    var idTrabajador: Long,
    @ColumnInfo(name = "id_asistencia_grupo")
    val idAsistenciaGrupo: Long,
    val editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idDescuento: String? = null,
        total: Double = 0.0,
        motivo: String = "",
        fecha: Date = Date(),
        idTrabajador: Long,
        idAsistenciaGrupo: Long,
        editado: Boolean = false,
    ) : this(
        id,
        idDescuento,
        total,
        motivo,
        fecha,
        idTrabajador,
        idAsistenciaGrupo,
        editado,
        false
    )

    fun dataCorrect(): Boolean = idTrabajador > 0 &&
            idAsistenciaGrupo > 0 &&
            total > 0 &&
            motivo.isNotEmpty()
}