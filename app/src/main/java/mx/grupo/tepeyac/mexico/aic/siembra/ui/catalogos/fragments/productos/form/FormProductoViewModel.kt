package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.productos.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.Producto
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoRepository

class FormProductoViewModel(app: Application, idProducto: Long?) : AndroidViewModel(app) {
    private val productoRepository: ProductoRepository by lazy {
        ProductoRepository(app)
    }

    private val errorLD = MutableLiveData<String>()
    val error: LiveData<String> = errorLD
    val producto: Producto =
        if (idProducto == null || idProducto == 0L) Producto()
        else productoRepository.getProducto(idProducto)

    fun updateData(): Boolean {
        if (producto.dataCorrect())
            productoRepository.insert(producto.copy(editado = true))
        else
            errorLD.value = "Datos erróneos"
        return producto.dataCorrect()
    }
}