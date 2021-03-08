package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad

data class AreaWithActividades(
    @Embedded
    val area: Area,
    @Relation(
        parentColumn = "id_interno",
        entityColumn = "id_area"
    )
    val actividades: List<Actividad>,
) {
    fun toSendAreaItem(): SendAreaItem =
        SendAreaItem(
            area.idArea,
            area.area,
            actividades.map { it.toSendActividadItem() }
        )
}