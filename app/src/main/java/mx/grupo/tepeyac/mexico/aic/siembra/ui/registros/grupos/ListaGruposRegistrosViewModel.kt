package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.grupos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.*
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.Grupo
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import java.util.*

class ListaGruposRegistrosViewModel(app: Application, val type: Int?) : AndroidViewModel(app) {
    private val asistenciaGrupoRepository: AsistenciaGrupoRepository by lazy {
        AsistenciaGrupoRepository(app)
    }

    fun getAsistencias(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithAsistencias>> =
        asistenciaGrupoRepository.getGruposAsistencias(start, end)

    fun getActividades(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithActividades>> =
        asistenciaGrupoRepository.getGruposActividades(start, end)

    fun getExtras(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithExtras>> =
        asistenciaGrupoRepository.getGruposExtras(start, end)

    fun getBonos(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithBonos>> =
        asistenciaGrupoRepository.getGruposBonos(start, end)

    fun getDescuentos(start: Date, end: Date): LiveData<List<AsistenciaGrupoWithDescuentos>> =
        asistenciaGrupoRepository.getGruposDescuentos(start, end)
}