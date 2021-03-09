package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import android.content.Context
import android.util.Log
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.ActividadTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.TipoActividadTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.Asistencia
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.Bono
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento.Descuento
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.Extra
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.TablaItem
import mx.grupo.tepeyac.mexico.aic.siembra.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AsistenciaGrupoRepository(context: Context) {
    private val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(context)
    }
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(context)
    }
    private val areaRepository: AreaRepository by lazy {
        AreaRepository(context)
    }
    private val asistenciaApi: AsistenciaApi =
        ServiceGenerator.createService(context, AsistenciaApi::class.java, "a")

    fun getAsistenciasGrupo(): List<AsistenciaGrupoItem> {
        return emptyList()
    }

    fun getRanchoID(id: String): Long? = ranchoRepository.getRanchoID(id)
    fun getRanchoID(id: Long): String? = ranchoRepository.getRanchoID(id)
    fun getTablaID(id: String): Long? = ranchoRepository.getTablaID(id)
    fun getTablaID(id: Long): String? = ranchoRepository.getTablaID(id)
    fun getActividadID(id: String): Long? = areaRepository.getActividadID(id)
    fun getActividadID(id: Long): String? = areaRepository.getActividadID(id)
    fun getTrabajadorID(id: String): Long? = grupoRepository.getTrabajadorID(id)
    fun getTrabajadorID(id: Long): String? = grupoRepository.getTrabajadorID(id)

    fun downloadAsistenciaGrupo() {
        asistenciaApi.getAsistenciasGrupo().enqueue(object : Callback<ResponseAsistenciaGrupoList> {
            override fun onResponse(
                call: Call<ResponseAsistenciaGrupoList>,
                response: Response<ResponseAsistenciaGrupoList>
            ) {
                response.body()?.let { a ->
                    val asistenciaGrupo: List<AsistenciaGrupoWithInfo> =
                        parseAsistenciasGrupo(a.asistencias)
                    Log.i("TAG", "onResponse: $asistenciaGrupo")
                }
            }

            override fun onFailure(call: Call<ResponseAsistenciaGrupoList>, t: Throwable) {
                Log.e("TAG", "onFailure: AAAA")
                t.printStackTrace()
            }
        })
    }

    fun parseAsistenciasGrupo(data: List<AsistenciaGrupoItem>): List<AsistenciaGrupoWithInfo> =
        data.mapNotNull { asistencia ->
            val idRancho: Long = getRanchoID(asistencia.rancho.id) ?: return@mapNotNull null
            return@mapNotNull AsistenciaGrupoWithInfo(
                asistencia.toEntity(idRancho),
                getAsistencias(asistencia.trabajadores),
                getExtras(asistencia),
                getBonos(asistencia),
                getDescuentos(asistencia),
                getActividades(asistencia),
            )
        }

    private fun getAsistencias(trabajadores: List<TrabajadorAsistenciaItem>): List<Asistencia> =
        trabajadores.mapNotNull { trabajador ->
            val idTrabajador: Long = getTrabajadorID(trabajador.trabajador.id)
                ?: return@mapNotNull null
            return@mapNotNull trabajador.getAsistencia(idTrabajador)
        }

    private fun getExtras(asistencia: AsistenciaGrupoItem): List<Extra> =
        asistencia.trabajadores.mapNotNull { trabajador ->
            val idTrabajador: Long = getTrabajadorID(trabajador.trabajador.id)
                ?: return@mapNotNull null
            return@mapNotNull trabajador.getExtras(idTrabajador, asistencia.fecha)
        }.flatten()

    private fun getBonos(asistencia: AsistenciaGrupoItem): List<Bono> =
        asistencia.trabajadores.mapNotNull { trabajador ->
            val idTrabajador: Long = getTrabajadorID(trabajador.trabajador.id)
                ?: return@mapNotNull null
            return@mapNotNull trabajador.getBonos(idTrabajador, asistencia.fecha)
        }.flatten()

    private fun getDescuentos(asistencia: AsistenciaGrupoItem): List<Descuento> =
        asistencia.trabajadores.mapNotNull { trabajador ->
            val idTrabajador: Long = getTrabajadorID(trabajador.trabajador.id)
                ?: return@mapNotNull null
            return@mapNotNull trabajador.getDescuentos(idTrabajador, asistencia.fecha)
        }.flatten()

    private fun getActividades(asistencia: AsistenciaGrupoItem): List<ActividadTrabajador> =
        asistencia.trabajadores.mapNotNull { trabajador ->
            val idTrabajador: Long = getTrabajadorID(trabajador.trabajador.id)
                ?: return@mapNotNull null
            val fecha: Date = asistencia.fecha
            return@mapNotNull listOf(
                getActividades(idTrabajador, fecha, trabajador.asistencia.actividades),
                getExtras(idTrabajador, fecha, trabajador.extras),
                getBonos(idTrabajador, fecha, trabajador.bonos),
            ).flatten()
        }.flatten()

    private fun getActividades(
        idTrabajador: Long,
        fecha: Date,
        actividades: List<ActividadTrabajadorItem>
    ): List<ActividadTrabajador> =
        getActividadesTrabajador(
            idTrabajador,
            fecha,
            actividades,
            TipoActividadTrabajador.REGULAR
        )

    private fun getExtras(
        idTrabajador: Long,
        fecha: Date,
        extras: List<ExtraTrabajadorItem>
    ): List<ActividadTrabajador> =
        extras.map { extra ->
            getActividadesTrabajador(
                idTrabajador,
                fecha,
                extra.actividades,
                TipoActividadTrabajador.EXTRA,
            )
        }.flatten()

    private fun getBonos(
        idTrabajador: Long,
        fecha: Date,
        bonos: List<BonoTrabajadorItem>
    ): List<ActividadTrabajador> =
        bonos.map { bono ->
            getActividadesTrabajador(
                idTrabajador,
                fecha,
                bono.actividades,
                TipoActividadTrabajador.BONO,
            )
        }.flatten()

    private fun getActividadesTrabajador(
        idTrabajador: Long,
        fecha: Date,
        actividades: List<ActividadTrabajadorItem>,
        type: TipoActividadTrabajador
    ): List<ActividadTrabajador> =
        actividades.mapNotNull { actividad ->
            val idActividad: Long = getActividadID(actividad.id) ?: return@mapNotNull null
            return@mapNotNull getActividadesTrabajadorTabla(
                idTrabajador,
                idActividad,
                fecha,
                actividad.tablas,
                type
            )
        }.flatten()

    private fun getActividadesTrabajadorTabla(
        idTrabajador: Long,
        idActividad: Long,
        fecha: Date,
        tablas: List<TablaItem>,
        type: TipoActividadTrabajador
    ): List<ActividadTrabajador> =
        tablas.mapNotNull { tabla ->
            val idTabla: Long = getTablaID(tabla.id) ?: return@mapNotNull null
            return@mapNotNull ActividadTrabajador(
                idActividad = idActividad,
                idTrabajador = idTrabajador,
                idTabla = idTabla,
                fecha = fecha,
                type = type,
            )
            //TODO: Agregar idActividadTrabajador! ! !
        }
}