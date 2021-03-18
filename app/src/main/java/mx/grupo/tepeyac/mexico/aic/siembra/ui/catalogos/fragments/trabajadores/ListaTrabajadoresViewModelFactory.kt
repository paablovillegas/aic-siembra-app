package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.trabajadores

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListaTrabajadoresViewModelFactory(
    private val app: Application,
    private val id: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaTrabajadoresViewModel::class.java))
            return ListaTrabajadoresViewModel(app, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}