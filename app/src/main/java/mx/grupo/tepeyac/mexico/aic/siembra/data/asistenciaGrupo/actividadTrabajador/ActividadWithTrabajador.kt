package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla

data class ActividadWithTrabajador(
    @Embedded
    val actividadTrabajador: ActividadTrabajador,
    @Relation(
        parentColumn = "id_trabajador",
        entityColumn = "id_interno",
    )
    val trabajador: Trabajador,
    @Relation(
        parentColumn = "id_tabla",
        entityColumn = "id_interno",
    )
    val tabla: Tabla,
    @Relation(
        parentColumn = "id_activiad",
        entityColumn = "id_interno",
    )
    val actividad: Actividad,
)
