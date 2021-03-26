package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.asistencias

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.AsistenciaRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.AsistenciaWithTrabajador

class ListaAsistenciasViewModel(
    app: Application,
    idAsistenciaGrupo: Long
) : AndroidViewModel(app) {
    private val asistenciaRepository: AsistenciaRepository by lazy {
        AsistenciaRepository(app)
    }
    val asistencias: LiveData<List<AsistenciaWithTrabajador>> =
        asistenciaRepository.getAsistencias(idAsistenciaGrupo)
}