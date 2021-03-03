package mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo

import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoItem
import java.util.*

data class ResponseCicloList(
    val ok: Boolean,
    val ciclos: List<CicloItem>
)

data class ResponseCicloItem(
    val ok: Boolean,
    val ciclo: CicloItem,
)

data class CicloItem(
    val id: String,
    val ciclo: String,
    val tabla: String,
    val producto: ProductoItem,
    val fechas: FechasCiclo,
) {
    fun toEntity(): Ciclo = Ciclo(
        idCiclo = id,
        ciclo = ciclo,
        idTabla = tabla,
        idProducto = producto.id,
        fechaInicio = fechas.inicio,
        fechaSiembra = fechas.siembra,
        fechaCosecha = fechas.cosecha,
        fechaFin = fechas.fin,
    )
}

data class FechasCiclo(
    val inicio: Date,
    val siembra: Date? = null,
    val cosecha: Date? = null,
    val fin: Date? = null,
)
