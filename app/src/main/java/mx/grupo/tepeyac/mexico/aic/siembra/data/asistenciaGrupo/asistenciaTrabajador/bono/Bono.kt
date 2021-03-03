package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistenciaTrabajador.bono

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistenciaTrabajador.actividadTrabajador.ActividadTrabajador

@Entity
data class Bono(
    @ColumnInfo(name = "id_interno")
    var idInterno: Long = 0,
    var total: Double,
    @Ignore
    var actividades: List<ActividadTrabajador> = emptyList()
)