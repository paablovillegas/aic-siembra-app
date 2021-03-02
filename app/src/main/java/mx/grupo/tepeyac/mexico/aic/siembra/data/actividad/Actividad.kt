package mx.grupo.tepeyac.mexico.aic.siembra.data.actividad

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Actividad(
    @PrimaryKey
    var id_interno: Long = 0,
    var id_actividad: String? = null,
    var actividad: String,
    @Ignore
    var id_area: String? = null,
)