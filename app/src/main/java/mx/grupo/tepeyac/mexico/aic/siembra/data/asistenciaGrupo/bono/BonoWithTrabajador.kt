package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

data class BonoWithTrabajador(
    @Embedded
    val bono: Bono,
    @Relation(
        parentColumn = "id_trabajador",
        entityColumn = "id_interno",
    )
    val trabajador: Trabajador,
    /*
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
    */
)