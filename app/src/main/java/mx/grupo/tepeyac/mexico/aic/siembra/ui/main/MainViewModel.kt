package mx.grupo.tepeyac.mexico.aic.siembra.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(app: Application) : AndroidViewModel(app) {
    val ranchoRepository: RanchoRepository = RanchoRepository(app)
    val productoRepository: ProductoRepository = ProductoRepository(app)

    fun getRanchos() {
        ranchoRepository.downloadRanchos()
        productoRepository.downloadProductos()
    }
}