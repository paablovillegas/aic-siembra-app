package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.productos.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormProductoViewModelFactory(
    private val app: Application,
    private val id: Long?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormProductoViewModel::class.java))
            return FormProductoViewModel(app, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}