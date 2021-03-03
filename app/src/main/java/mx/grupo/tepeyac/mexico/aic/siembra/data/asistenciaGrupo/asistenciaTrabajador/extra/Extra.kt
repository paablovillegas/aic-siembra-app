package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistenciaTrabajador.extra

import androidx.room.ColumnInfo
import androidx.room.Ignore
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistenciaTrabajador.actividadTrabajador.ActividadTrabajador

data class Extra(
    @ColumnInfo(name = "id_interno")
    var idInterno: Long = 0,
    var horas: Int,
    var total: Double,
    @Ignore
    var actividades: List<ActividadTrabajador> = emptyList()
)