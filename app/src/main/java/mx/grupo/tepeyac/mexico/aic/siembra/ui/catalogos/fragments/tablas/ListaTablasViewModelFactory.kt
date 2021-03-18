package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.tablas

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListaTablasViewModelFactory(
    private val app: Application,
    private val idRancho: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaTablasViewModel::class.java))
            return ListaTablasViewModel(app, idRancho) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}