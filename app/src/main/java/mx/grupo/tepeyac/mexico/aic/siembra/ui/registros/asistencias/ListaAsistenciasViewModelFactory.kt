package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.asistencias

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListaAsistenciasViewModelFactory(
    private val app: Application,
    private val idAsistenciaGroup: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaAsistenciasViewModel::class.java))
            return ListaAsistenciasViewModel(app, idAsistenciaGroup) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}