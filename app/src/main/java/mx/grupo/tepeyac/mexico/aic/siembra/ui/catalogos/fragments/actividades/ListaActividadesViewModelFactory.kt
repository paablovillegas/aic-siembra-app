package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.actividades

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListaActividadesViewModelFactory(
    private val app: Application,
    private val id: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaActividadesViewModel::class.java))
            return ListaActividadesViewModel(app, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}