package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.grupos

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListaGruposRegistrosViewModelFactory(
    private val app: Application,
    private val id: Int?,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaGruposRegistrosViewModel::class.java))
            return ListaGruposRegistrosViewModel(app, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}