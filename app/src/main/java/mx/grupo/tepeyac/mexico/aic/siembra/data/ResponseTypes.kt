package mx.grupo.tepeyac.mexico.aic.siembra.data

import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.Rancho

data class ResponseRanchoList(
    val ok: Boolean,
    val ranchos: List<Rancho>
)

data class ResponseRanchoItem(
    val ok: Boolean,
    val ranchos: Rancho
)