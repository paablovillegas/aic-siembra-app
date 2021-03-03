package mx.grupo.tepeyac.mexico.aic.siembra.data

import mx.grupo.tepeyac.mexico.aic.siembra.data.area.Area
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.Rancho

data class ResponseRanchoList(
    val ok: Boolean,
    val ranchos: List<Rancho>
)

data class ResponseRanchoItem(
    val ok: Boolean,
    val ranchos: Rancho
)

data class ResponseAreaList(
    val ok: Boolean,
    val ranchos: List<Area>
)

data class ResponseAreaItem(
    val ok: Boolean,
    val ranchos: Area
)