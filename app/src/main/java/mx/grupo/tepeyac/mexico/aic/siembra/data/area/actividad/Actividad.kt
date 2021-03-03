package mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Actividad(
    @PrimaryKey
    var id_interno: Long = 0,
    @ColumnInfo(name = "id_actividad")
    var idActividad: String? = null,
    var actividad: String,
    @ColumnInfo(name = "id_area")
    var idArea: String? = null,
)