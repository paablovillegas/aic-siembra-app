package mx.grupo.tepeyac.mexico.aic.siembra.data.tabla

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Tabla(
    @PrimaryKey
    var id_interno: Long = 0,
    @SerializedName("_id")
    var idTabla: String? = null,
    var tabla: String,
    var editado: Boolean = false,

    @SerializedName("id_rancho")
    var idRancho: String,
)