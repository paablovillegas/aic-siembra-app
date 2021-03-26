package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.descuentos

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListaDescuentosViewModelFactory(
    private val app: Application,
    private val idAsistenciaGrupo: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaDescuentosViewModel::class.java))
            return ListaDescuentosViewModel(app, idAsistenciaGrupo) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}