package mx.grupo.tepeyac.mexico.aic.siembra.data.producto

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.AppDatabase
import mx.grupo.tepeyac.mexico.aic.siembra.network.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductoRepository(context: Context) {
    val productoDao: ProductoDao = AppDatabase.getInstance(context).productoDao
    val productoApi: ProductoApi = ServiceGenerator
        .createService(context, ProductoApi::class.java, "A")

    fun getProductos(): List<Producto> = productoDao.getProductos()
    fun getProductosLD(): LiveData<List<Producto>> = productoDao.getProductosLD()
    fun getProductoID(id: String): Long? = productoDao.getProductoID(id)
    fun getProductoID(id: Long): String? = productoDao.getProductoID(id)

    fun compareProductos(
        internos: List<Producto>,
        externos: List<Producto>
    ): List<Producto> {
        val inserts = externos.filter { externo ->
            internos.find { interno ->
                interno.idProducto == externo.idProducto
            } == null
        }
        val updates = externos.mapNotNull { externo ->
            val interno = internos.find { interno ->
                interno.idProducto == externo.idProducto && !interno.editado
            }
            if (interno != null) externo.copy(id = interno.id)
            else null
        }
        val deletes = internos.filter { interno ->
            externos.find { externo ->
                interno.idProducto == externo.idProducto
            } == null
        }.map { i -> i.copy(delete = true) }
        Log.i("TAG", "downloadProductos: I:${inserts.size} U:${updates.size} D:${deletes.size}")
        return listOf(inserts, updates, deletes).flatten()
    }

    private fun updateDatabaseTablas(productos: List<Producto>) {
        productos.forEach { producto ->
            when {
                producto.delete -> productoDao.delete(producto)
                producto.id > 0 -> productoDao.update(producto)
                else -> productoDao.insert(producto)
            }
        }
    }

    fun downloadProductos() {
        productoApi.getProductos().enqueue(object : Callback<ResponseProductoList> {
            override fun onResponse(
                call: Call<ResponseProductoList>,
                response: Response<ResponseProductoList>
            ) {
                response.body()?.let {
                    val productos = compareProductos(getProductos(), it.toProductosEntities())
                    updateDatabaseTablas(productos)
                }
            }

            override fun onFailure(call: Call<ResponseProductoList>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun insertProducto(producto: Producto) {

    }

    fun updateProducto(producto: Producto) {

    }

}