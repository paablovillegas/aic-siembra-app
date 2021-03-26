package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.descuentos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento.DescuentoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento.DescuentoWithTrabajador

class ListaDescuentosViewModel(
    app: Application,
    idAsistenciaGrupo: Long,
) : AndroidViewModel(app) {
    private val descuentoRepository: DescuentoRepository by lazy {
        DescuentoRepository(app)
    }
    val descuentos: LiveData<List<DescuentoWithTrabajador>> =
        descuentoRepository.getAsistenciasGrupo(idAsistenciaGrupo)
}