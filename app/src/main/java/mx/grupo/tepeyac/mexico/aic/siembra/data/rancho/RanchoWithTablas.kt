package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla

data class RanchoWithTablas(
    @Embedded
    val rancho: Rancho,
    @Relation(
        parentColumn = "id_interno",
        entityColumn = "id_rancho"
    )
    val tablas: List<Tabla>
) {
    fun toRanchoItem(): RanchoItem? =
        rancho.toRanchoItem(tablas.mapNotNull { it.toTablaItem() })

    fun toSendRanchoItem(): SendRanchoItem = SendRanchoItem(this)
}