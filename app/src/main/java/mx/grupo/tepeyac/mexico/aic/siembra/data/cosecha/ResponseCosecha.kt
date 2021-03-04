package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha

import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.CicloItem
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoItem
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoItem
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.TablaItem
import java.util.*

data class ResponseCosechaList(
    val ok: Boolean,
    val cosechas: List<CosechaItem>
)

data class ResponseCosechaItem(
    val ok: Boolean,
    val cosecha: CosechaItem
)

data class CosechaItem(
    @SerializedName("_id")
    val id: String,
    val folio: Long,
    val destino: ResponseDestino?,
    val presentacion: ResponsePresentacion?,
    val fecha: Date,
    @SerializedName("fecha_salida")
    val fechaSalida: Date,
    val transporte: ResponseTransporte?,
    val inocuidad: ResponseInocuidad?,
    val tipo: String,
    val ciclos: List<ResponseCiclos>,
)

data class ResponseDestino(
    @SerializedName("_id")
    val id: String,
    val cliente: String,
)

data class ResponsePresentacion(
    @SerializedName("_id")
    val id: String,
)

data class ResponseTransporte(
    @SerializedName("_id")
    val id: String,
)

data class ResponseInocuidad(
    @SerializedName("_id")
    val id: String,
)

data class ResponseCiclos(
    @SerializedName("_id")
    val id: String,
    val ciclo: CicloItem,
    val tabla: TablaItem,
    val producto: ProductoItem,
    val corte: List<ResponseCorte>,
    val empaque: List<ResponseEmpaque>,
    val granel: List<ResponseGranel>,
)

data class ResponseCorte(
    @SerializedName("_id")
    val id: String,
    @SerializedName("cuadrilla", alternate = ["grupo"])
    val grupo: List<GrupoItem>,
    val costos: ResponseCostos,
)

data class ResponseEmpaque(
    @SerializedName("_id")
    val id: String,
    @SerializedName("cuadrilla", alternate = ["grupo"])
    val grupo: List<GrupoItem>,
    val costos: ResponseCostos,
)

data class ResponseGranel(
    @SerializedName("_id")
    val id: String,
    @SerializedName("cuadrilla", alternate = ["grupo"])
    val grupo: List<GrupoItem>,
    val costos: ResponseCostos,
)

data class ResponseCostos(
    val total: Double,
    val capitan: Double,
    val apuntador: Double,
    val resagador: Double,
    val colores: Double?,
    val cajas: Double?,
    val kilogramos: Double?,
)
