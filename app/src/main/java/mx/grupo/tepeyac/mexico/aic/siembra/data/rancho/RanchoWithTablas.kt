package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla

data class RanchoWithTablas(
    @Embedded
    val rancho: Rancho,
    @Relation(
        parentColumn = "id_rancho",
        entityColumn = "id_rancho"
    )
    val tablas: List<Tabla>
)