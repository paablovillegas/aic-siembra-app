package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.corte

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Corte(
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    var idInterno: Long = 0,
)