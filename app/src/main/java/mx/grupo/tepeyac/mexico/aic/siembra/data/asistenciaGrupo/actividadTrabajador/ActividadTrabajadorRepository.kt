package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador

import android.content.Context
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase

class ActividadTrabajadorRepository(context: Context) {
    val dao: ActividadTrabajadorDao by lazy {
        AppDatabase.getInstance(context).actividadTrabajadorDao
    }

    fun insert(actividadTrabajador: ActividadTrabajador) = dao.insert(actividadTrabajador)
}