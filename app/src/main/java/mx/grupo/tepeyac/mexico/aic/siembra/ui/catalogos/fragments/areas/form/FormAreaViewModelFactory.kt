package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.areas.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormAreaViewModelFactory(
    private val app: Application,
    private val id: Long?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormAreaViewModel::class.java))
            return FormAreaViewModel(app, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }

}