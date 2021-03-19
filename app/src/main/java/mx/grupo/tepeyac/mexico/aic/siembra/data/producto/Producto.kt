package mx.grupo.tepeyac.mexico.aic.siembra.data.producto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Producto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_producto")
    var idProducto: String? = null,
    var producto: String = "",
    var editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idProducto: String? = null,
        producto: String,
        editado: Boolean = false,
    ) : this(id, idProducto, producto, editado, false)

    fun dataCorrect(): Boolean =
        producto.isNotEmpty()
}