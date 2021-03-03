package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Area(
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_area")
    var idArea: String? = null,
    var area: String,
    var editado: Boolean = false,
)