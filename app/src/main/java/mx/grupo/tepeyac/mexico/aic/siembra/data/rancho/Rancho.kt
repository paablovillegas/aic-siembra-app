package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import androidx.room.*
import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla

@Entity
data class Rancho(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_rancho")
    var idRancho: String?,
    var rancho: String,
    var alias: String?,
    var editado: Boolean = false,
)