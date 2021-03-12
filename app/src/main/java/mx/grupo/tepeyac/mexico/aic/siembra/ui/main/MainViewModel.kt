package mx.grupo.tepeyac.mexico.aic.siembra.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.CicloRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {
    private val ranchoRepository: RanchoRepository = RanchoRepository(app)
    private val productoRepository: ProductoRepository = ProductoRepository(app)
    private val areaRepository: AreaRepository = AreaRepository(app)
    private val grupoRepository: GrupoRepository = GrupoRepository(app)
    private val cicloRepository: CicloRepository = CicloRepository(app)
    private val asistenciaGrupoRepository: AsistenciaGrupoRepository = AsistenciaGrupoRepository(app)

    fun getInfo() {
        //ranchoRepository.downloadRanchos()
        //productoRepository.downloadProductos()
        //areaRepository.downloadAreas()
        //grupoRepository.downloadGrupos()
        //cicloRepository.downloadCiclos()
        asistenciaGrupoRepository.downloadAsistenciaGrupo()
    }
}