package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase
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
    private val asistenciaGrupoDao: AsistenciaGrupoDao by lazy {
        AppDatabase.getInstance(context).asistenciaGrupoDao
    }
    private val asistenciaApi: AsistenciaApi =
        ServiceGenerator.createService(context, AsistenciaApi::class.java, "a")

    fun getRanchoID(id: String): Long? = ranchoRepository.getRanchoID(id)
    fun getRanchoID(id: Long): String? = ranchoRepository.getRanchoID(id)
    fun getTablaID(id: String): Long? = ranchoRepository.getTablaID(id)
    fun getTablaID(id: Long): String? = ranchoRepository.getTablaID(id)
    fun getActividadID(id: String): Long? = areaRepository.getActividadID(id)
    fun getActividadID(id: Long): String? = areaRepository.getActividadID(id)
    fun getTrabajadorID(id: String): Long? = grupoRepository.getTrabajadorID(id)
    fun getTrabajadorID(id: Long): String? = grupoRepository.getTrabajadorID(id)

    fun getAsistenciasGrupo(): List<AsistenciaGrupoWithInfo> =
        asistenciaGrupoDao.getAsistenciaGrupos()

    fun getGruposAsistencias(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithAsistencias>> =
        asistenciaGrupoDao.getGruposAsistencias(start, end).distinctUntilChanged()

    fun getGruposActividades(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithActividades>> =
        asistenciaGrupoDao.getGruposActividades(start, end).distinctUntilChanged()

    fun getGruposExtras(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithExtras>> =
        asistenciaGrupoDao.getGruposExtras(start, end).distinctUntilChanged()

    fun getGruposBonos(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithBonos>> =
        asistenciaGrupoDao.getGruposBonos(start, end).distinctUntilChanged()

    fun getGruposDescuentos(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithDescuentos>> =
        asistenciaGrupoDao.getGruposDescuentos(start, end).distinctUntilChanged()

    fun compareAsistenciasGrupos(
        internos: List<AsistenciaGrupoWithInfo>,
        externos: List<AsistenciaGrupoWithInfo>
    ): List<List<AsistenciaGrupoWithInfo>> {
        val inserts = externos.filter { e ->
            internos.find { i ->
                i.asistenciaGrupo.idAsistenciaGrupo == e.asistenciaGrupo.idAsistenciaGrupo
            } == null
        }
        val updates = externos.mapNotNull { e ->
            val interno: AsistenciaGrupoWithInfo? = internos.find { i ->
                i.asistenciaGrupo.idAsistenciaGrupo == e.asistenciaGrupo.idAsistenciaGrupo
            }
            if (interno != null)
                return@mapNotNull null
            return@mapNotNull null
        }
        val deletes = internos
            .filter { i ->
                externos.find { e ->
                    e.asistenciaGrupo.idAsistenciaGrupo == i.asistenciaGrupo.idAsistenciaGrupo
                } == null
            }
            .map { a ->
                a.copy(
                    asistencias = a.asistencias.map { it.copy(delete = true) },
                    extras = a.extras.map { it.copy(delete = true) },
                    bonos = a.bonos.map { it.copy(delete = true) },
                    descuento = a.descuento.map { it.copy(delete = true) },
                    actividades = a.actividades.map { it.copy(delete = true) }
                )
            }
        return listOf(inserts, updates, deletes)
    }

    fun downloadAsistenciaGrupo() {
        asistenciaApi.getAsistenciasGrupo().enqueue(object : Callback<ResponseAsistenciaGrupoList> {
            override fun onResponse(
                call: Call<ResponseAsistenciaGrupoList>,
                response: Response<ResponseAsistenciaGrupoList>
            ) {
                response.body()?.let { a ->
                    val asistenciaGrupo: List<AsistenciaGrupoWithInfo> =
                        parseAsistenciasGrupo(a.asistencias)
                    val data = compareAsistenciasGrupos(getAsistenciasGrupo(), asistenciaGrupo)
                    Log.i("TAG", "onResponse: $data")
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
            val idGrupo: Long =
                grupoRepository.getGrupoID(asistencia.grupo.id) ?: return@mapNotNull null
            val idRancho: Long = getRanchoID(asistencia.rancho.id) ?: return@mapNotNull null
            return@mapNotNull AsistenciaGrupoWithInfo(
                asistencia.toEntity(idGrupo, idRancho),
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
        actividades: List<ActividadTrabajadorItem>,
        idAsistenciaGrupo: Long = 0,
    ): List<ActividadTrabajador> =
        getActividadesTrabajador(
            idAsistenciaGrupo,
            idTrabajador,
            fecha,
            actividades,
            TipoActividadTrabajador.REGULAR,
        )

    private fun getExtras(
        idTrabajador: Long,
        fecha: Date,
        extras: List<ExtraTrabajadorItem>,
        idAsistenciaGrupo: Long = 0,
    ): List<ActividadTrabajador> =
        extras.map { extra ->
            getActividadesTrabajador(
                idAsistenciaGrupo,
                idTrabajador,
                fecha,
                extra.actividades,
                TipoActividadTrabajador.EXTRA,
            )
        }.flatten()

    private fun getBonos(
        idTrabajador: Long,
        fecha: Date,
        bonos: List<BonoTrabajadorItem>,
        idAsistenciaGrupo: Long = 0,
    ): List<ActividadTrabajador> =
        bonos.map { bono ->
            getActividadesTrabajador(
                idAsistenciaGrupo,
                idTrabajador,
                fecha,
                bono.actividades,
                TipoActividadTrabajador.BONO,
            )
        }.flatten()

    private fun getActividadesTrabajador(
        idAsistenciaGrupo: Long,
        idTrabajador: Long,
        fecha: Date,
        actividades: List<ActividadTrabajadorItem>,
        type: TipoActividadTrabajador
    ): List<ActividadTrabajador> =
        actividades.mapNotNull { actividad ->
            val idActividad: Long = getActividadID(actividad.actividad.id)
                ?: return@mapNotNull null
            return@mapNotNull getActividadesTrabajadorTabla(
                idAsistenciaGrupo,
                idTrabajador,
                idActividad,
                fecha,
                actividad.tablas,
                type,
            )
        }.flatten()

    private fun getActividadesTrabajadorTabla(
        idAsistenciaGrupo: Long,
        idTrabajador: Long,
        idActividad: Long,
        fecha: Date,
        tablas: List<TablaItem>?,
        type: TipoActividadTrabajador
    ): List<ActividadTrabajador> =
        tablas?.mapNotNull { tabla ->
            val idTabla: Long = getTablaID(tabla.id) ?: return@mapNotNull null
            return@mapNotNull ActividadTrabajador(
                idActividad = idActividad,
                idTrabajador = idTrabajador,
                idAsistenciaGrupo = idAsistenciaGrupo,
                idTabla = idTabla,
                fecha = fecha,
                type = type,
            )
            //TODO: Agregar idActividadTrabajador! ! !
        } ?: listOf(
            ActividadTrabajador(
                idActividad = idActividad,
                idTrabajador = idTrabajador,
                idAsistenciaGrupo = idAsistenciaGrupo,
                idTabla = null,
                fecha = fecha,
                type = type,
            )
        )
}