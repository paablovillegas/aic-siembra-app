package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.tablas.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormTablaViewModelFactory(
    private val app: Application,
    private val idRancho: Long,
    private val id: Long?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormTablaViewModel::class.java))
            return FormTablaViewModel(app, idRancho, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }

}