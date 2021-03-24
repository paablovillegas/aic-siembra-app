package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

data class DescuentoWithTrabajador(
    @Embedded
    val descuento: Descuento,
    @Relation(
        parentColumn = "id_trabajador",
        entityColumn = "id_interno",
    )
    val trabajador: Trabajador,
)
