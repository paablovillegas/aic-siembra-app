package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.grupos.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.Grupo
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository

class FormGrupoViewModel(app: Application, id: Long?) : AndroidViewModel(app) {
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(app)
    }

    private val errorLD = MutableLiveData<String>()
    val error: LiveData<String> = errorLD
    val tiposGrupo: List<String> = listOf("Base", "Cuadrilla", "Cosecha")
    val grupo: Grupo =
        if (id == null || id == 0L) Grupo(tipoGrupo = "Cuadrilla")
        else grupoRepository.getGrupo(id)

    fun updateData(): Boolean {
        val dataCorrect = grupo.dataCorrect()
        if (dataCorrect)
            grupoRepository.insert(grupo.normalize().copy(editado = true))
        else
            errorLD.value = "Datos Erróneos"
        return dataCorrect
    }
}