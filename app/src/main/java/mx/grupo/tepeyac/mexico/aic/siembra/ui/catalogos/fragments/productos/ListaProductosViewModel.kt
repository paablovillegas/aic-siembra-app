package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.productos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.Producto
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoRepository

class ListaProductosViewModel(app: Application) : AndroidViewModel(app) {
    private val productoRepository: ProductoRepository by lazy {
        ProductoRepository(app)
    }

    val productos: LiveData<List<Producto>> = productoRepository.getProductosLD()
}