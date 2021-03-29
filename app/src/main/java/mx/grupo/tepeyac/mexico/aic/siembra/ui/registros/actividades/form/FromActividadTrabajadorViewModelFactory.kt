package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.actividades.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FromActividadTrabajadorViewModelFactory(
    private val app: Application,
    private val idAsistenciaGrupo: Long,
    private val idRancho: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormActividadTrabajadorViewModel::class.java))
            return FormActividadTrabajadorViewModel(app, idAsistenciaGrupo, idRancho) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}