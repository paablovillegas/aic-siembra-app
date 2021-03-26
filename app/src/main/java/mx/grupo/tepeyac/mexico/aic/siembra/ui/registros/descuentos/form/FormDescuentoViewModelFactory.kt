package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.descuentos.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FormDescuentoViewModelFactory(
    private val app: Application,
    private val idGrupo: Long,
    private val id: Long?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormDescuentoViewModel::class.java))
            return FormDescuentoViewModel(app, idGrupo, id) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}