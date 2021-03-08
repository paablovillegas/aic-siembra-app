package mx.grupo.tepeyac.mexico.aic.siembra.data.producto

import retrofit2.Call
import retrofit2.http.*

interface ProductoApi {
    @GET("productos")
    fun getProductos(): Call<ResponseProductoList>

    @POST("productos")
    fun insertProducto(@Body body: SendProductoItem): Call<ResponseProductoItem>

    @PUT("productos/{id_producto}")
    fun updateProducto(
        @Path(value = "id_producto") id: String,
        @Body producto: SendProductoItem,
    ): Call<ResponseProductoItem>
}