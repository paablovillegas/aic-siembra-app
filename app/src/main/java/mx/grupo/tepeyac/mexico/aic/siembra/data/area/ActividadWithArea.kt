package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad

data class ActividadWithArea(
    @Embedded
    val actividad: Actividad,
    @Relation(
        parentColumn = "id_area",
        entityColumn = "id_interno",
    )
    val area: Area,
)