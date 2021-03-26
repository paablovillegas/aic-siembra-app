package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia

import android.content.Context
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase

class AsistenciaRepository(context: Context) {
    private val asistenciaDao: AsistenciaDao by lazy {
        AppDatabase.getInstance(context).asistenciaDao
    }

    fun insert(asistencia: Asistencia) = asistenciaDao.insert(asistencia)

    fun getAsistencia(id: Long): Asistencia = asistenciaDao.getAsistencia(id)

    fun getAsistencias(idAsistenciaGrupo: Long): LiveData<List<AsistenciaWithTrabajador>> =
        asistenciaDao.getAsistenciasGrupo(idAsistenciaGrupo)
}