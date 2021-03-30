package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.bonos.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

class FormBonoViewModelFactory(
    private val trabajadores: List<Trabajador>,
    private val idAsistenciaGroup: Long,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormBonoViewModel::class.java))
            return FormBonoViewModel(trabajadores, idAsistenciaGroup) as T
        throw IllegalArgumentException("Uknown ViewModel class")
    }
}