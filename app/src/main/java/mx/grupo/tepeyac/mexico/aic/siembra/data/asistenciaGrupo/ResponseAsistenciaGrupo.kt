package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.Extra
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoItem
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoItem
import java.util.*

data class ResponseAsistenciaGrupoList(
    val ok: Boolean,
    val asistencias: List<AsistenciaGrupoItem>,
)

data class ResponseAsistenciaGrupoItem(
    val ok: Boolean,
    val asistencia: AsistenciaGrupoItem,
)

data class AsistenciaGrupoItem(
    @SerializedName("_id")
    val id: String,
    @SerializedName("lugar_trabajo")
    val grupo: GrupoItem,
    val rancho: RanchoItem,
    val flete: Double?,
    val fecha: Date,
    val trabajadores: List<TrabajadorAsistenciaItem>
)

data class TrabajadorAsistenciaItem(
    @SerializedName("_id")
    val id: String,
    val trabajador: Trabajador,
    val asistencia: AsistenciaTrabajadorItem,
    val extras: List<ExtraTrabajadorItem>,
    val bonos: List<BonoTrabajadorItem>,
    val descuentos: List<DescuentoTrabajadorItem>,
)

data class AsistenciaTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val credencial: String,
    val entrada: Date,
    val salida: Date?,
    val actividades: List<ActividadTrabajadorItem>
)

data class ExtraTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val horas: Int,
    val total: Double,
    val actividades: List<ActividadTrabajadorItem>
) {
    fun toEntity(idTrabajador: String): Extra = Extra(
        idTrabajador = idTrabajador,
        idExtra = id,
        horas = horas,
        total = total,
    )
}

data class BonoTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val total: Double,
    val actividades: List<ActividadTrabajadorItem>
)

data class DescuentoTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val total: Double,
    val motivo: String,
    val fecha: Date,
)

data class ActividadTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val actividad: String,
    //TODO: Checar esta parte
    val tablas: List<String>
)
