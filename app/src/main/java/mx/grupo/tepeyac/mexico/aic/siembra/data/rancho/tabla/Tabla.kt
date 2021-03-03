package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Tabla(
    @PrimaryKey
    var id_interno: Long = 0,
    @ColumnInfo(name = "id_tabla")
    var idTabla: String? = null,
    var tabla: String,
    var editado: Boolean = false,
    @ColumnInfo(name = "id_rancho")
    var idRancho: String,
)