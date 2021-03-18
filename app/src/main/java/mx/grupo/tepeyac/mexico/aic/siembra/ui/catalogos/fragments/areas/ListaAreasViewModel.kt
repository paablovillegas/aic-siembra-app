package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.areas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaWithActividades

class ListaAreasViewModel(app: Application) : AndroidViewModel(app) {
    private val areaRepository: AreaRepository by lazy {
        AreaRepository(app)
    }

    val areas: LiveData<List<AreaWithActividades>> = areaRepository.getAreasLD()
}