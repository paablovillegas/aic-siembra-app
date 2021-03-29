package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.ActividadWithArea
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupo
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.ActividadTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.ActividadTrabajadorRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.TipoActividadTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.Bono
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.Extra
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.Grupo
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoWithTablas
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla
import java.util.*

class RegistrosViewModel(app: Application) : AndroidViewModel(app) {
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(app)
    }
    private val asistenciaGrupoRepository: AsistenciaGrupoRepository by lazy {
        AsistenciaGrupoRepository(app)
    }
    private val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(app)
    }
    private val areaRepository: AreaRepository by lazy {
        AreaRepository(app)
    }
    private val actividadTrabajadorRepository: ActividadTrabajadorRepository by lazy {
        ActividadTrabajadorRepository(app)
    }

    val registros: List<String> = listOf(
        "Asistencias",
        "Actividades",
        "Extras",
        "Bonos",
        "Descuentos"
    )
    private val calendar: Calendar = Calendar.getInstance()
    private val fecha: Date
    val startDate: Date = Date()
    val endDate: Date = Date()
    var idRancho: Long = 0L
    var idAsistenciaGrupo: Long = 0L

    private val actividadesIds = MutableLiveData<List<Long>>()
    val liveActividadesIds: LiveData<List<Long>> = actividadesIds
    private val trabajadoresIds = MutableLiveData<List<Long>>()
    val liveTrabajadoresIds: LiveData<List<Long>> = trabajadoresIds
    private val tablasIds = MutableLiveData<List<Long>>()
    val liveTablasIds: LiveData<List<Long>> = tablasIds
    private val error = MutableLiveData<String>()
    val errorLD: LiveData<String> = error

    init {
        calendar.time = Date()
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        fecha = calendar.time
        setStartEnd()
    }

    fun getRanchos(): List<RanchoWithTablas> = ranchoRepository.getRanchos()

    fun getGruposDisponibles(): List<Grupo> =
        grupoRepository.geGruposDisponibles(startDate, endDate)

    fun addGrupo(id: Long) {
        val asistenciaGrupo = AsistenciaGrupo(grupo = id, fecha = startDate, rancho = idRancho)
        asistenciaGrupoRepository.insert(asistenciaGrupo)
    }

    fun getDay(): Int = calendar.get(Calendar.DAY_OF_MONTH)

    fun getMonth(): Int = calendar.get(Calendar.MONTH)

    fun getYear(): Int = calendar.get(Calendar.YEAR)

    fun setDate(year: Int, month: Int, day: Int) {
        calendar.set(Calendar.DAY_OF_MONTH, day)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        fecha.time = calendar.timeInMillis
        startDate.time = fecha.time
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        endDate.time = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        setStartEnd()
    }

    private fun setStartEnd() {
        startDate.time = fecha.time
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        calendar.add(Calendar.SECOND, -1)
        endDate.time = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        calendar.add(Calendar.SECOND, 1)
    }

    fun getDay(add: Int): Long {
        calendar.add(Calendar.DAY_OF_YEAR, add)
        val fecha: Long = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, -add)
        return fecha
    }

    fun getTablasLD(): LiveData<List<Tabla>> =
        ranchoRepository.getTablasLD(idRancho)

    fun getTablas(): List<Tabla> =
        ranchoRepository.getTablas(idRancho)

    fun setTablas(lista: List<Long>) {
        tablasIds.value = lista
    }

    fun getTrabajadoresLD(): LiveData<List<Trabajador>> =
        grupoRepository.getTrabajadoresLD(idAsistenciaGrupo)

    fun getTrabajadores(): List<Trabajador> =
        grupoRepository.getTrabajadores(idAsistenciaGrupo)

    fun setTrabajadores(lista: List<Long>) {
        trabajadoresIds.value = lista
    }

    fun getActividadesLD(): LiveData<List<ActividadWithArea>> =
        areaRepository.getActividadesLD()

    fun getActividades(): List<ActividadWithArea> =
        areaRepository.getActividades()

    fun setActividades(lista: List<Long>) {
        actividadesIds.value = lista
    }

    fun registrarActividad(): Boolean {
        actividadesIds.value?.let { acts ->
            tablasIds.value?.let { tablas ->
                trabajadoresIds.value?.let { trabajadores ->
                    if (acts.isEmpty() || tablas.isEmpty() || trabajadores.isEmpty()) {
                        error.value = "Seleccionar al menos 1 elementp de cada tipo"
                        return false
                    }
                    acts.forEach { actividad ->
                        tablas.forEach { tabla ->
                            trabajadores.forEach { trabajador ->
                                val actividadTrabajador = ActividadTrabajador(
                                    idActividad = actividad,
                                    idTrabajador = trabajador,
                                    idTabla = tabla,
                                    idAsistenciaGrupo = idAsistenciaGrupo,
                                    fecha = Date(),
                                    type = TipoActividadTrabajador.REGULAR,
                                )
                                actividadTrabajadorRepository.insert(actividadTrabajador)
                            }
                        }
                    }
                    return true
                }
            }
        }
        error.value = "Seleccionar al menos 1 elementp de cada tipo"
        return false
    }

    fun registrarExtras(horas: Int): Boolean {
        actividadesIds.value?.let { acts ->
            tablasIds.value?.let { tablas ->
                trabajadoresIds.value?.let { trabajadores ->
                    if (acts.isEmpty() || tablas.isEmpty() || trabajadores.isEmpty()) {
                        error.value = "Seleccionar al menos 1 elementp de cada tipo"
                        return false
                    }
                    trabajadores.forEach { trabajador ->
                        val extra = Extra(
                            horas = horas,
                            total = 0.0, //TODO: Agregar aqui total
                            idTrabajador = trabajador,
                            idAsistenciaGrupo = idAsistenciaGrupo,
                            fecha = Date(),
                        )
                        acts.forEach { actividad ->
                            tablas.forEach { tabla ->
                                val actividadTrabajador = ActividadTrabajador(
                                    idActividad = actividad,
                                    idTrabajador = trabajador,
                                    idTabla = tabla,
                                    idAsistenciaGrupo = idAsistenciaGrupo,
                                    fecha = Date(),
                                    type = TipoActividadTrabajador.REGULAR,
                                )
                                //actividadTrabajadorRepository.insert(actividadTrabajador)
                            }
                        }
                    }
                    return true
                }
            }
        }
        error.value = "Seleccionar al menos 1 elementp de cada tipo"
        return false
    }

    fun registrarBonos(): Boolean {
        actividadesIds.value?.let { acts ->
            tablasIds.value?.let { tablas ->
                trabajadoresIds.value?.let { trabajadores ->
                    if (acts.isEmpty() || tablas.isEmpty() || trabajadores.isEmpty()) {
                        error.value = "Seleccionar al menos 1 elementp de cada tipo"
                        return false
                    }
                    trabajadores.forEach { trabajador ->
                        val bono = Bono(
                            total = 0.0,//TODO: Aqui
                            idTrabajador = trabajador,
                            idAsistenciaGrupo = idAsistenciaGrupo,
                            fecha = Date(),
                        )
                        acts.forEach { actividad ->
                            tablas.forEach { tabla ->
                                val actividadTrabajador = ActividadTrabajador(
                                    idActividad = actividad,
                                    idTrabajador = trabajador,
                                    idTabla = tabla,
                                    idAsistenciaGrupo = idAsistenciaGrupo,
                                    fecha = Date(),
                                    type = TipoActividadTrabajador.REGULAR,
                                )
                                //actividadTrabajadorRepository.insert(actividadTrabajador)
                            }
                        }
                    }
                    return true
                }
            }
        }
        error.value = "Seleccionar al menos 1 elementp de cada tipo"
        return false
    }
}