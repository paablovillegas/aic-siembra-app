package mx.grupo.tepeyac.mexico.aic.siembra.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {
    val ranchoRepository: RanchoRepository = RanchoRepository(app)

    fun getRanchos() {
        ranchoRepository.downloadRanchos()
        ranchoRepository.syncRanchos()
        val rancho = ranchoRepository.getRanchos()[1]
        ranchoRepository.updateRancho(rancho)
    }
}