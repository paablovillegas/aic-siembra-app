package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.asistencias.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.Asistencia
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.AsistenciaRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

class FormAsistenciaViewModel(
    app: Application,
    idGrupoAsistencia: Long,
    id: Long?
) : AndroidViewModel(app) {
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(app)
    }
    private val asistenciaRepository: AsistenciaRepository by lazy {
        AsistenciaRepository(app)
    }

    private val errorLD = MutableLiveData<String>()
    val error: LiveData<String> = errorLD
    val asistencia: Asistencia =
        if (id == null || id == 0L)
            Asistencia(idAsistenciaGrupo = idGrupoAsistencia)
        else asistenciaRepository.getAsistencia(id)
    val trabajadores: LiveData<List<Trabajador>> =
        grupoRepository.getTrabajadoresLD(idGrupoAsistencia)

    fun getTrabajador(): String =
        if (asistencia.idTrabajador > 0)
            grupoRepository.getTrabajador(asistencia.idTrabajador).getNombreCompleto()
        else
            ""

    fun setTrabajador(id: Long) {
        asistencia.idTrabajador = id
    }

    fun registrar(): Boolean {
        val dataCorrect = asistencia.dataCorrect()
        if (dataCorrect)
            asistenciaRepository.insert(asistencia)
        else
            errorLD.value = "Datos erróneos"
        return dataCorrect
    }
}