package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.grupos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoWithTrabajadores

class ListaGruposViewModel(app: Application) : AndroidViewModel(app) {
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(app)
    }

    val grupos: LiveData<List<GrupoWithTrabajadores>> = grupoRepository.getGruposLD()
}