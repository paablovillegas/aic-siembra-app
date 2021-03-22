package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.trabajadores.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormTrabajadorViewModelFactory(
    private val app: Application,
    private val idGrupo: Long,
    private val idTrabajador: Long?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormTrabajadorViewModel::class.java))
            return FormTrabajadorViewModel(app, idGrupo, idTrabajador) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}