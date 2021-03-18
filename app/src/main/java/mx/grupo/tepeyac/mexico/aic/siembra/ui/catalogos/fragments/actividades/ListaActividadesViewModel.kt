package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.actividades

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaRepository

class ListaActividadesViewModel(app: Application, id: Long) : AndroidViewModel(app) {
    private val areaRepository: AreaRepository by lazy {
        AreaRepository(app)
    }
    val area = areaRepository.getAreaLD(id)
}