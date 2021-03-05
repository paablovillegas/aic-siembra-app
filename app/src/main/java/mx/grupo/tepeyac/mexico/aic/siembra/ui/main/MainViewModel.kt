package mx.grupo.tepeyac.mexico.aic.siembra.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.Rancho
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoWithTablas
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla

class MainViewModel(app: Application) : AndroidViewModel(app) {
    val ranchoRepository: RanchoRepository = RanchoRepository(app)

    fun getRanchos() {
        ranchoRepository.downloadRanchos()
        ranchoRepository.syncRanchos()
    }
}