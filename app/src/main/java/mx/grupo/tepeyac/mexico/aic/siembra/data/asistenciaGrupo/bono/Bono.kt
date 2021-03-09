package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Bono(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_bono")
    val idBono: String? = null,
    var total: Double,
    @ColumnInfo(name = "id_trabajador")
    val idTrabajador: Long,
    val fecha: Date,
    val editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idBono: String? = null,
        total: Double,
        idTrabajador: Long,
        fecha: Date,
        editado: Boolean = false,
    ) : this(id, idBono, total, idTrabajador, fecha, editado, false)
}