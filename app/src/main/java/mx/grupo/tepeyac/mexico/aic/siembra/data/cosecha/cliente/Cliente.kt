package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.cliente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_cliente")
    val idCliente: String? = null,
    var cliente: String,
    val editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idCliente: String? = null,
        cliente: String,
        editado: Boolean = false
    ) : this(id, idCliente, cliente, editado, false)
}