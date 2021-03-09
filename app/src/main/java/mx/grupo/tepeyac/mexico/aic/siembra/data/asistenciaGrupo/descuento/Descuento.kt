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
    var total: Double,
    var motivo: String,
    val fecha: Date,
    @ColumnInfo(name = "id_trabajador")
    val idTrabajador: Long,
    val editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idDescuento: String? = null,
        total: Double,
        motivo: String,
        fecha: Date,
        idTrabajador: Long,
        editado: Boolean = false,
    ) : this(id, idDescuento, total, motivo, fecha, idTrabajador, editado, false)
}