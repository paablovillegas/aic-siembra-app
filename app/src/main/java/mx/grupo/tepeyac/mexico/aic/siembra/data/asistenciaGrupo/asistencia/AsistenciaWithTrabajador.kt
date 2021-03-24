package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

data class AsistenciaWithTrabajador(
    @Embedded
    val asistencia: Asistencia,
    @Relation(
        parentColumn = "id_trabajador",
        entityColumn = "id_interno",
    )
    val trabajador: Trabajador,
)
