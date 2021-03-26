package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.asistencias.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormAsistenciaViewModelFactory(
    private val app: Application,
    private val idAsistenciaGrupo: Long,
    private val id: Long?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormAsistenciaViewModel::class.java))
            return FormAsistenciaViewModel(app, idAsistenciaGrupo, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}