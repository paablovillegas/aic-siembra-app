package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.tablas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoWithTablas

class ListaTablasViewModel(app: Application, idRancho: Long) : AndroidViewModel(app) {
    private val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(app)
    }

    val rancho: LiveData<RanchoWithTablas> = ranchoRepository.getRanchoLD(idRancho)
}