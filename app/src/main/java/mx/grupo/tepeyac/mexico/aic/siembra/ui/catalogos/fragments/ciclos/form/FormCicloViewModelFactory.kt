package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ciclos.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormCicloViewModelFactory(
    private val app: Application,
    private val idTabla: Long,
    private val id: Long?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormCicloViewModel::class.java))
            return FormCicloViewModel(app, idTabla, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }

}