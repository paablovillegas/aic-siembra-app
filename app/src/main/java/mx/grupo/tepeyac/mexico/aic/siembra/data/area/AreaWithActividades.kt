package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad

data class AreaWithActividades(
    @Embedded
    val area: Area,
    @Relation(
        parentColumn = "id_area",
        entityColumn = "id_area"
    )
    val actividades: List<Actividad>,
)