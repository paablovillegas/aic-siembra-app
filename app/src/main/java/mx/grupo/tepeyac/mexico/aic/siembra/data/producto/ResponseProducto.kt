package mx.grupo.tepeyac.mexico.aic.siembra.data.producto

import com.google.gson.annotations.SerializedName

data class ResponseProductoList(
    val ok: Boolean,
    val productos: List<ProductoItem>
) {
    fun toProductosEntities(): List<Producto> =
        productos.map { it.toEntity() }
}

data class ResponseProductoItem(
    val ok: Boolean,
    val producto: ProductoItem
)

data class ProductoItem(
    @SerializedName("_id")
    val id: String,
    val producto: String
) {
    fun toEntity(): Producto = Producto(
        idProducto = id,
        producto = producto,
    )
}

data class SendProductoItem(
    val idProducto: String?,
    val producto: String,
)