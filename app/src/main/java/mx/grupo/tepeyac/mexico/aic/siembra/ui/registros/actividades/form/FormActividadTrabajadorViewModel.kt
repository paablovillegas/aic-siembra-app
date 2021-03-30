package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.actividades.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaWithActividades
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoWithTablas

class FormActividadTrabajadorViewModel(
    app: Application,
    idAsistenciaGrupo: Long,
) : AndroidViewModel(app) {
    private val areaRepository: AreaRepository by lazy {
        AreaRepository(app)
    }
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(app)
    }
    private val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(app)
    }

    val actividades: LiveData<List<AreaWithActividades>> =
        areaRepository.getAreasLD()
    val tablas: LiveData<List<RanchoWithTablas>> =
        ranchoRepository.getRanchosLD()
    val trabajadores: LiveData<List<Trabajador>> =
        grupoRepository.getTrabajadoresLD(idAsistenciaGrupo)
}