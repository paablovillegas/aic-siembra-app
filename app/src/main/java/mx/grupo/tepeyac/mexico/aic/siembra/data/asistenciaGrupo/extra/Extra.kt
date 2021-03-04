package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra

import androidx.room.ColumnInfo
import androidx.room.Ignore

data class Extra(
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_extra")
    val idExtra: String,
    var horas: Int,
    var total: Double,
    val editado: Boolean = false,
    @ColumnInfo(name = "id_trabajador")
    val idTrabajador: String,
    @Ignore
    val eliminar: Boolean = false,
)