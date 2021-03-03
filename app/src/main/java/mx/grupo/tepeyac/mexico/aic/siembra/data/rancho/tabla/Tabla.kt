package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Tabla(
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_tabla")
    var idTabla: String? = null,
    var tabla: String,
    var editado: Boolean = false,
    @ColumnInfo(name = "id_rancho")
    var idRancho: String?,
    @Ignore
    val delete: Boolean = false,
)