package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia

import android.content.Context
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase

class AsistenciaRepository(context: Context) {
    private val asistenciaDao: AsistenciaDao by lazy {
        AppDatabase.getInstance(context).asistenciaDao
    }

    fun getAsistencias(idAsistenciaGrupo: Long): LiveData<List<AsistenciaWithTrabajador>> =
        asistenciaDao.getAsistenciasGrupo(idAsistenciaGrupo)
}