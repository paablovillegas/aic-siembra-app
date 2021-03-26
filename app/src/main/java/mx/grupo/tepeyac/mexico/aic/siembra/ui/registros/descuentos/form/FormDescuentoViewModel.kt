package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.descuentos.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento.Descuento
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento.DescuentoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

class FormDescuentoViewModel(
    app: Application,
    idGrupoAsistencia: Long,
    id: Long?
) : AndroidViewModel(app) {
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(app)
    }
    private val descuentoRepository: DescuentoRepository by lazy {
        DescuentoRepository(app)
    }

    private val errorLD = MutableLiveData<String>()
    val error: LiveData<String> = errorLD
    val descuento: Descuento =
        if (id == null || id == 0L)
            Descuento(idAsistenciaGrupo = idGrupoAsistencia, idTrabajador = 0)
        else descuentoRepository.getDescuento(id)
    val trabajadores: LiveData<List<Trabajador>> =
        grupoRepository.getTrabajadoresLD(idGrupoAsistencia)

    fun getTrabajador(): String =
        if (descuento.idTrabajador > 0)
            grupoRepository.getTrabajador(descuento.idTrabajador).getNombreCompleto()
        else
            ""

    fun setTrabajador(id: Long) {
        descuento.idTrabajador = id
    }

    fun registrar(): Boolean {
        val dataCorrect = descuento.dataCorrect()
        if (dataCorrect)
            descuentoRepository.insert(descuento)
        else
            errorLD.value = "Datos erróneos"
        return dataCorrect
    }
}