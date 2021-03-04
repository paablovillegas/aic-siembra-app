package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.granel

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Granel(
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    val idInterno: Long = 0,
)