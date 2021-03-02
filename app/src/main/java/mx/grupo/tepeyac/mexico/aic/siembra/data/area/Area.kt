package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.actividad.Actividad

@Entity
data class Area(
    @PrimaryKey
    var id_interno: Long = 0,
    @SerializedName("id_area")
    var idArea: String? = null,
    var area: String,
    var editado: Boolean = false,
    @Ignore
    val actividades: List<Actividad> = emptyList(),
)