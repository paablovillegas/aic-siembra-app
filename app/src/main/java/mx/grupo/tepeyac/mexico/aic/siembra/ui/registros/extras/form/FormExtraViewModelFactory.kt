package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.extras.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormExtraViewModelFactory(
    private val app: Application,
    private val id: Long?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormExtraViewModel::class.java))
            return FormExtraViewModel(app, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}