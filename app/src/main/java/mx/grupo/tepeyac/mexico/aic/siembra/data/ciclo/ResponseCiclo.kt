package mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo

import com.google.gson.annotations.SerializedName
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
    @SerializedName("_id")
    val id: String,
    val ciclo: String,
    val tabla: String,
    val producto: ProductoItem,
    val fechas: FechasCiclo,
) {
    fun toEntity(idTabla: Long = 0, idProducto: Long = 0): Ciclo =
        Ciclo(
            idCiclo = id,
            ciclo = ciclo,
            idTabla = idTabla,
            idProducto = idProducto,
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

data class SendCicloItem(
    val idCiclo: String?,
    val ciclo: String,
    val tabla: String,
    val producto: String,
    @SerializedName("fecha_inicio")
    val fechaInicio: Date,
    @SerializedName("fecha_siembra")
    val fechaSiembra: Date?,
    @SerializedName("fecha_cosecha")
    val fechaCosecha: Date?,
    @SerializedName("fecha_fin")
    val fechaFin: Date?,
) {
    constructor(
        ciclo: Ciclo,
        idTabla: String,
        idProducto: String
    ) :
            this(
                ciclo.idCiclo,
                ciclo.ciclo,
                idTabla,
                idProducto,
                ciclo.fechaInicio,
                ciclo.fechaSiembra,
                ciclo.fechaCosecha,
                ciclo.fechaFin,
            )
}
