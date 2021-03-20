package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.grupos.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormGrupoViewModelFactory(
    private val app: Application,
    private val id: Long?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormGrupoViewModel::class.java))
            return FormGrupoViewModel(app, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }

}