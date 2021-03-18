package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ciclos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.Ciclo
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.CicloRepository

class ListaCiclosViewModel(app: Application, idTabla: Long): AndroidViewModel(app) {
    val cicloRepository: CicloRepository by lazy {
        CicloRepository(app)
    }

    val ciclos: LiveData<List<Ciclo>> = cicloRepository.getCiclosLD()
}