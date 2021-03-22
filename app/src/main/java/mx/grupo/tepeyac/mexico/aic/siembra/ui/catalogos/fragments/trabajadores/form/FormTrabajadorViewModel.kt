package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.trabajadores.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.Grupo
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

class FormTrabajadorViewModel(app: Application, idGrupo: Long, idTrabajador: Long?) :
    AndroidViewModel(app) {
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(app)
    }

    private val errorLD = MutableLiveData<String>()
    val error: LiveData<String> = errorLD
    val grupo: Grupo = grupoRepository.getGrupo(idGrupo)
    val trabajador: Trabajador =
        if (idTrabajador == null || idTrabajador == 0L) Trabajador(idGrupo = idGrupo)
        else grupoRepository.getTrabajador(idTrabajador)
    val generos: List<String> = listOf("Masculino", "Femenino")

    fun setGenero(index: Int) {
        trabajador.genero = generos[index]
    }

    fun updateData(): Boolean {
        val dataCorrect: Boolean = trabajador.dataCorrect()
        if (dataCorrect)
            grupoRepository.insert(trabajador.copy(editado = true))
        else
            errorLD.value = "Datos erróneos"
        return dataCorrect
    }
}