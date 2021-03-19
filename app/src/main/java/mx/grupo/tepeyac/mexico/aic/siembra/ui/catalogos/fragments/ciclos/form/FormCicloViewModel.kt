package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ciclos.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.Ciclo
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.CicloRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.Producto
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.ProductoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.Rancho
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla
import java.util.*

class FormCicloViewModel(app: Application, idTabla: Long, idCiclo: Long?) : AndroidViewModel(app) {
    private val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(app)
    }
    private val cicloRepository: CicloRepository by lazy {
        CicloRepository(app)
    }
    private val productoRepository: ProductoRepository by lazy {
        ProductoRepository(app)
    }

    private val errorLD = MutableLiveData<String>()
    val error: LiveData<String> = errorLD
    val tabla: Tabla = ranchoRepository.getTabla(idTabla)
    val rancho: Rancho = ranchoRepository.getRancho(tabla.idRancho)
    val ciclo: Ciclo =
        if (idCiclo != null && idCiclo > 0) cicloRepository.getCiclo(idCiclo)
        else Ciclo(idTabla = idTabla, fechaInicio = Date())
    val productos: List<Producto> = productoRepository.getProductos()
    val productosString: List<String> = productos.map { it.producto }

    fun getProducto(): String? = productos.find { it.id == ciclo.idProducto }?.producto

    fun setProducto(index: Int) {
        ciclo.idProducto = productos[index].id
    }

    fun registrarCiclo(): Boolean {
        if (ciclo.dataCorrect())
            cicloRepository.insert(ciclo.copy(editado = true))
        else
            errorLD.value = "Datos erróneos"
        return ciclo.dataCorrect()
    }
}