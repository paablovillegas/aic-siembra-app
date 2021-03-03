package mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Actividad(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_actividad")
    var idActividad: String? = null,
    var actividad: String,
    val editado: Boolean = false,
    @ColumnInfo(name = "id_area")
    var idArea: String? = null,
    @Ignore
    val delete: Boolean = false,
)