package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.trabajadores

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoWithTrabajadores

class ListaTrabajadoresViewModel(app: Application, idGrupo: Long) : AndroidViewModel(app) {
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(app)
    }

    val grupo: LiveData<GrupoWithTrabajadores> = grupoRepository.getGrupoLD(idGrupo)
}