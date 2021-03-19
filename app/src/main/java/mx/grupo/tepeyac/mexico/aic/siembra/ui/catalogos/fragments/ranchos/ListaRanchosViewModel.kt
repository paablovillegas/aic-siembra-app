package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ranchos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoWithTablas

class ListaRanchosViewModel(app: Application) : AndroidViewModel(app) {
    val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(app)
    }

    val ranchos: LiveData<List<RanchoWithTablas>> = ranchoRepository.getRanchosLD()
}