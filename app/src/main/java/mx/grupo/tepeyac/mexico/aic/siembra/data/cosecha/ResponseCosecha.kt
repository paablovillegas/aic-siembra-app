package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha

import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.cliente.Cliente
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.corte.Corte
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.empaque.Empaque
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.granel.Granel
import mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.transporte.Transporte
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoItem
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
    val presentacion: String, //TODO: ???
    val fecha: Date,
    @SerializedName("fecha_salida")
    val fechaSalida: Date,
    val transporte: ResponseTransporte?,
    val revision: ResponseRevision?,
    val tipo: String,
    val rancho: String, //TODO: ???
    val producto: String, //TODO: ???
    val corte: List<ResponseCorte>,
    val empaque: List<ResponseEmpaque>,
    val granel: List<ResponseGranel>,
) {
    fun getListaCorte(): List<Corte> =
        corte.map { it.getListaCorte(id) }.flatten()

    fun getListaEmpaque(): List<Empaque> =
        empaque.map { it.getListaEmpaque(id) }.flatten()

    fun getListaGranel(): List<Granel> =
        granel.map { it.getListaGranel(id) }.flatten()
}

data class ResponseDestino(
    @SerializedName("_id")
    val id: String,
    val cliente: String,
) {
    fun toEntity(): Cliente =
        Cliente(
            idCliente = id,
            cliente = cliente
        )
}

data class ResponseTransporte(
    @SerializedName("_id")
    val id: String,
    val chofer: String,
    val placas: String,
    val celular: String,
    @SerializedName("modelo", alternate = ["marca"])
    val modeloVehiculo: String,
    val tipo: String,
) {
    fun toEntity(idCosecha: String): Transporte =
        Transporte(
            idTransporte = id,
            chofer = chofer,
            placas = placas,
            celular = celular,
            modeloVehiculo = modeloVehiculo,
            tipo = tipo,
            idCosecha = idCosecha,
        )
}

data class ResponseRevision(
    @SerializedName("_id")
    val id: String,
    val limpio: Boolean,
    @SerializedName("mal_olor")
    val malOlor: Boolean,
    val cubierto: Boolean,
    @SerializedName("fuga_aceite")
    val fugaAceite: Boolean,
    @SerializedName("requisitos_cliente", alternate = ["req_cliente"])
    val reqCliente: Boolean,
    @SerializedName("buenas_practicas")
    val buenasPracticas: Boolean,
)

data class ResponseCorte(
    @SerializedName("_id")
    val id: String,
    @SerializedName("cuadrilla", alternate = ["grupo"])
    val grupo: GrupoItem,
    val ciclos: List<ResponseCiclo>,
) {
    fun getListaCorte(idCosecha: String): List<Corte> =
        ciclos.mapNotNull {
            it.toCorteEntity(
                idCosecha,
                grupo.id,
            )
        }
}

data class ResponseEmpaque(
    @SerializedName("_id")
    val id: String,
    @SerializedName("cuadrilla", alternate = ["grupo"])
    val grupo: GrupoItem,
    val ciclos: List<ResponseCiclo>,
) {
    fun getListaEmpaque(idCosecha: String): List<Empaque> =
        ciclos.mapNotNull {
            it.toEmpaqueEntity(
                idCosecha,
                grupo.id,
            )
        }
}

data class ResponseGranel(
    @SerializedName("_id")
    val id: String,
    @SerializedName("cuadrilla", alternate = ["grupo"])
    val grupo: GrupoItem,
    val ciclos: List<ResponseCiclo>,
) {
    fun getListaGranel(idCosecha: String): List<Granel> =
        ciclos.mapNotNull {
            it.toGranelEntity(
                idCosecha,
                grupo.id,
            )
        }
}

data class ResponseCiclo(
    @SerializedName("_id")
    val id: String,
    val ciclo: String,
    val tabla: String,
    val costos: ResponseCostos,
) {
    fun toCorteEntity(
        idCosecha: String,
        idCuadrilla: String,
    ): Corte? =
        if (costos.colotes == null)
            null
        else Corte(
            idCorte = id, //TODO: ???
            idCosecha = idCosecha,
            idCiclo = id,
            idCuadrilla = idCuadrilla,
            colotes = costos.colotes,
            flete = costos.flete,
            capitan = costos.capitan,
            apuntador = costos.apuntador,
            resagador = costos.resagador,
        )

    fun toEmpaqueEntity(
        idCosecha: String,
        idCuadrilla: String,
    ): Empaque? =
        if (costos.cajas == null)
            null
        else Empaque(
            idCorte = id, //TODO: ???
            idCosecha = idCosecha,
            idCiclo = id,
            idCuadrilla = idCuadrilla,
            cajas = costos.cajas,
            flete = costos.flete,
            capitan = costos.capitan,
            apuntador = costos.apuntador,
            resagador = costos.resagador,
        )

    fun toGranelEntity(
        idCosecha: String,
        idCuadrilla: String,
    ): Granel? =
        if (costos.kilogramos == null)
            null
        else Granel(
            idCorte = id, //TODO: ???
            idCosecha = idCosecha,
            idCiclo = id,
            idCuadrilla = idCuadrilla,
            kilogramos = costos.kilogramos,
            flete = costos.flete,
            capitan = costos.capitan,
            apuntador = costos.apuntador,
            resagador = costos.resagador,
        )
}

data class ResponseCostos(
    val total: Double,
    val flete: Double,
    val capitan: Double,
    val apuntador: Double,
    val resagador: Double,
    val colotes: Int?,
    val cajas: Int?,
    val kilogramos: Int?,
)
