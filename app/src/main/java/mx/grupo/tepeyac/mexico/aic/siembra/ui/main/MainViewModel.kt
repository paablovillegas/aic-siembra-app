package mx.grupo.tepeyac.mexico.aic.siembra.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.Area
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaWithActividades
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.CicloRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {
    val ranchoRepository: RanchoRepository = RanchoRepository(app)
    val productoRepository: ProductoRepository = ProductoRepository(app)
    val areaRepository: AreaRepository = AreaRepository(app)
    val grupoRepository: GrupoRepository = GrupoRepository(app)
    val cicloRepository: CicloRepository = CicloRepository(app)

    fun getInfo() {
        ranchoRepository.downloadRanchos()
        productoRepository.downloadProductos()
        areaRepository.downloadAreas()
        grupoRepository.downloadGrupos()
        cicloRepository.downloadCiclos()
    }
}