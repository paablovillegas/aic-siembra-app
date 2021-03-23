package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.grupos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository

class ListaGruposRegistrosViewModel(app: Application, val type: Int?) : AndroidViewModel(app) {
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(app)
    }
}