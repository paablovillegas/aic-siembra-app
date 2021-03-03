package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.empaque

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Empaque(
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    var idInterno: Long = 0,
)