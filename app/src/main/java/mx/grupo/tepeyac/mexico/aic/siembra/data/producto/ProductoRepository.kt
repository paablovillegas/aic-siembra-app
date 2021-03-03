package mx.grupo.tepeyac.mexico.aic.siembra.data.producto

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class ProductoRepository {
    fun getProductos(): List<ProductoItem> {
        val data =
            "{\"ok\":true,\"productos\":[{\"_id\":\"60397652f5e0a43f2018f93e\",\"producto\":\"Brócoli\",\"__v\":0}]}"
        val gson: Gson = GsonBuilder().create()
        val productosResponse = gson.fromJson(data, ResponseProductoList::class.java)
        return productosResponse.productos
    }

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
        return listOf(inserts, updates, deletes).flatten()
    }

}