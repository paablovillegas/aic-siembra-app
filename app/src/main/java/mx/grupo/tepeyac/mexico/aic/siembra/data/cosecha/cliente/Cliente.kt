package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.cliente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cliente(
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    var idInterno: Long = 0,
    var cliente: String,
)