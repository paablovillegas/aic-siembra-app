package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.extras.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

class FormExtraViewModelFactory(
    private val trabajadores: List<Trabajador>,
    private val idAsistenciaGroup: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormExtraViewModel::class.java))
            return FormExtraViewModel(trabajadores, idAsistenciaGroup) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}