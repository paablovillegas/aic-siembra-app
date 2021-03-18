package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ciclos

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListaCiclosViewModelFactory(
    private val app: Application,
    private val id: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaCiclosViewModel::class.java))
            return ListaCiclosViewModel(app, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}