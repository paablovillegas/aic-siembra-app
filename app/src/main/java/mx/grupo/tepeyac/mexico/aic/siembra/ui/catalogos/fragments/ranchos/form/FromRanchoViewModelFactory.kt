package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ranchos.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FromRanchoViewModelFactory(
    private val app: Application,
    private val id: Long?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormRanchoViewModel::class.java))
            return FormRanchoViewModel(app, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}