package mx.grupo.tepeyac.mexico.aic.siembra.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository

class MainViewModel(app: Application) : AndroidViewModel(app) {
    val ranchoRepository: RanchoRepository = RanchoRepository(app)

    fun getRanchos() {
        ranchoRepository.downloadRanchos()

        val ranchos = ranchoRepository.getRanchos2()
    }
}