package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.CicloRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository

class CatalogosViewModel(app: Application) : AndroidViewModel(app) {
    private val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(app)
    }
    private val cicloRepository: CicloRepository by lazy {
        CicloRepository(app)
    }
    private val areaRepository: AreaRepository by lazy {
        AreaRepository(app)
    }
    private val grupoRepository: GrupoRepository by lazy {
        GrupoRepository(app)
    }
    private val productoRepository: ProductoRepository by lazy {
        ProductoRepository(app)
    }

    val catalogos: List<String> = listOf(
        "Ranchos",
        "Areas",
        "Grupos",
        "Productos",
    )

    fun downloadRanchos() {
        ranchoRepository.downloadRanchos()
    }

    fun downloadCiclos() {
        cicloRepository.downloadCiclos()
    }

    fun downloadAreas() {
        areaRepository.downloadAreas()
    }

    fun downloadGrupos() {
        grupoRepository.downloadGrupos()
    }

    fun downloadProductos() {
        productoRepository.downloadProductos()
    }

}