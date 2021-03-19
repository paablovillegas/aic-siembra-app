package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.actividades.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormActividadViewModelFactory(
    private val app: Application,
    private val idArea: Long,
    private val id: Long?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormActividadViewModel::class.java))
            return FormActividadViewModel(app, idArea, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }

}