package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento

import android.content.Context
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase

class DescuentoRepository(context: Context) {
    val descuentoDao: DescuentoDao by lazy {
        AppDatabase.getInstance(context).descuentoDao
    }

    fun insert(descuento: Descuento) = descuentoDao.insert(descuento)

    fun getDescuento(id: Long): Descuento = descuentoDao.getDescuento(id)

    fun getAsistenciasGrupo(idAsistenciaGrupo: Long): LiveData<List<DescuentoWithTrabajador>> =
        descuentoDao.getAsistenciasGrupo(idAsistenciaGrupo)
}