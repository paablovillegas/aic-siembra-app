package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.ActividadItem
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.ActividadTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.TipoActividadTrabajador
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
    fun getAsistencias(): List<Asistencia> =
        trabajadores.map { it.getAsistencia() }

    fun getExras(): List<Extra> =
        trabajadores.map { it.getExtras(fecha) }
            .flatten()

    fun getBonos(): List<Bono> =
        trabajadores.map { it.getBonos(fecha) }
            .flatten()

    fun getDescuentos(): List<Descuento> =
        trabajadores.map { it.getDescuentos(fecha) }
            .flatten()

    fun getActividadesTrabajador(): List<ActividadTrabajador> =
        trabajadores.map { it.getActividades(fecha) }
            .flatten()
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
    fun getAsistencia(): Asistencia =
        asistencia.toEntity(trabajador.id)

    fun getExtras(fecha: Date): List<Extra> =
        extras.map { it.toEntity(trabajador.id, fecha) }

    fun getBonos(fecha: Date): List<Bono> =
        bonos.map { it.toEntity(trabajador.id, fecha) }

    fun getDescuentos(fecha: Date): List<Descuento> =
        descuentos.map { it.toEntity(trabajador.id, fecha) }

    fun getActividades(fecha: Date): List<ActividadTrabajador> =
        listOf(
            asistencia.getActividadesList(trabajador.id, fecha),
            extras.map {
                it.getActividadesList(trabajador.id, fecha)
            }.flatten(),
            bonos.map {
                it.getActividadesList(trabajador.id, fecha)
            }.flatten(),
        ).flatten()
}

data class AsistenciaTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val credencial: String,
    val entrada: Date,
    val salida: Date?,
    val actividades: List<ActividadTrabajadorItem>
) {
    fun toEntity(idTrabajador: String): Asistencia = Asistencia(
        idAsistencia = id,
        entrada = entrada,
        salida = salida,
        idTrabajador = idTrabajador,
    )

    fun getActividadesList(
        idTrabajador: String,
        fecha: Date
    ): List<ActividadTrabajador> =
        actividades.map {
            it.toListEntities(
                idTrabajador,
                fecha,
                TipoActividadTrabajador.REGULAR
            )
        }.flatten()
}

data class ExtraTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val horas: Int,
    val total: Double,
    val actividades: List<ActividadTrabajadorItem>
) {
    fun toEntity(idTrabajador: String, fecha: Date): Extra = Extra(
        idTrabajador = idTrabajador,
        idExtra = id,
        horas = horas,
        total = total,
        fecha = fecha,
    )

    fun getActividadesList(
        idTrabajador: String,
        fecha: Date
    ): List<ActividadTrabajador> =
        actividades.map {
            it.toListEntities(
                idTrabajador,
                fecha,
                TipoActividadTrabajador.EXTRA
            )
        }.flatten()
}

data class BonoTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val total: Double,
    val actividades: List<ActividadTrabajadorItem>
) {
    fun toEntity(idTrabajador: String, fecha: Date): Bono = Bono(
        idBono = id,
        total = total,
        idTrabajador = idTrabajador,
        fecha = fecha,
    )

    fun getActividadesList(
        idTrabajador: String,
        fecha: Date
    ): List<ActividadTrabajador> =
        actividades.map {
            it.toListEntities(
                idTrabajador,
                fecha,
                TipoActividadTrabajador.BONO
            )
        }.flatten()
}

data class DescuentoTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val total: Double,
    val motivo: String,
) {
    fun toEntity(idTrabajador: String, fecha: Date): Descuento = Descuento(
        idDescuento = id,
        total = total,
        motivo = motivo,
        idTrabajador = idTrabajador,
        fecha = fecha,
    )
}

data class ActividadTrabajadorItem(
    @SerializedName("_id")
    val id: String,
    val actividad: ActividadItem,
    val tablas: List<TablaItem>
) {
    fun toListEntities(
        idTrabajador: String,
        fecha: Date,
        type: TipoActividadTrabajador
    ): List<ActividadTrabajador> =
        tablas.map {
            ActividadTrabajador(
                idActividadTrabajador = id,
                idActividad = actividad.id,
                idTrabajador = idTrabajador,
                idTabla = it.id,
                fecha = fecha,
                type = type
            )
        }
}
