package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.ActividadItem
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.Asistencia
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.Bono
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento.Descuento
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.Extra
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoItem
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.ResponseTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoItem
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.TablaItem
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
) {
    fun toEntity(idGrupo: Long, idRancho: Long): AsistenciaGrupo =
        AsistenciaGrupo(
            idAsistenciaGrupo = id,
            grupo = idGrupo,
            rancho = idRancho,
            flete = flete,
            fecha = fecha,
        )
}

data class TrabajadorAsistenciaItem(
    @SerializedName("_id")
    val id: String,
    val trabajador: ResponseTrabajador,
    val asistencia: AsistenciaTrabajadorItem,
    val extras: List<ExtraTrabajadorItem>,
    val bonos: List<BonoTrabajadorItem>,
    val descuentos: List<DescuentoTrabajadorItem>,
) {
    fun getAsistencia(idTrabajador: Long = 0): Asistencia =
        asistencia.toEntity(idTrabajador)

    fun getExtras(idTrabajador: Long, fecha: Date): List<Extra> =
        extras.map { it.toEntity(idTrabajador, fecha) }

    fun getBonos(idTrabajador: Long, fecha: Date): List<Bono> =
        bonos.map { it.toEntity(idTrabajador, fecha) }

    fun getDescuentos(idTrabajador: Long, fecha: Date): List<Descuento> =
        descuentos.map { it.toEntity(idTrabajador, fecha) }
}

data class AsistenciaTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val credencial: String,
    val entrada: Date,
    val salida: Date?,
    val actividades: List<ActividadTrabajadorItem>
) {
    fun toEntity(idTrabajador: Long, idAsistenciaGrupo: Long = 0): Asistencia = Asistencia(
        idAsistencia = id,
        entrada = entrada,
        salida = salida,
        idTrabajador = idTrabajador,
        idAsistenciaGrupo = idAsistenciaGrupo,
    )
}

data class ExtraTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val horas: Int,
    val total: Double,
    val actividades: List<ActividadTrabajadorItem>
) {
    fun toEntity(idTrabajador: Long, fecha: Date, idAsistenciaGrupo: Long = 0): Extra = Extra(
        idTrabajador = idTrabajador,
        idAsistenciaGrupo = idAsistenciaGrupo,
        idExtra = id,
        horas = horas,
        total = total,
        fecha = fecha,
    )
}

data class BonoTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val total: Double,
    val actividades: List<ActividadTrabajadorItem>
) {
    fun toEntity(idTrabajador: Long, fecha: Date, idAsistenciaGrupo: Long = 0): Bono = Bono(
        idBono = id,
        total = total,
        idTrabajador = idTrabajador,
        idAsistenciaGrupo = idAsistenciaGrupo,
        fecha = fecha,
    )
}

data class DescuentoTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val total: Double,
    val motivo: String,
) {
    fun toEntity(idTrabajador: Long, fecha: Date, idAsistenciaGrupo: Long = 0): Descuento =
        Descuento(
            idDescuento = id,
            total = total,
            motivo = motivo,
            idTrabajador = idTrabajador,
            idAsistenciaGrupo = idAsistenciaGrupo,
            fecha = fecha,
        )
}

data class ActividadTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val actividad: ActividadItem,
    val tablas: List<TablaItem>?
)

data class SendAsistenciaGrupoItem(
    val idAsistenciaGrupo: String?,
)