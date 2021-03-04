package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.cliente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_cliente")
    val idCliente: String? = null,
    var cliente: String,
)