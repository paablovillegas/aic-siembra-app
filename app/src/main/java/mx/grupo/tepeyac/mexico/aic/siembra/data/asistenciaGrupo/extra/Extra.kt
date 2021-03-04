package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra

import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

data class Extra(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_extra")
    val idExtra: String? = null,
    var horas: Int,
    var total: Double,
    @ColumnInfo(name = "id_trabajador")
    val idTrabajador: String,
    val fecha: Date,
    val editado: Boolean = false,
    @Ignore
    val eliminar: Boolean = false,
)