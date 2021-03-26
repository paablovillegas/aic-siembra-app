package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.bonos.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormBonoViewModelFactory(
    private val app: Application,
    private val id: Long?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormBonoViewModel::class.java))
            return FormBonoViewModel(app, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}