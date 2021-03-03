package mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Ciclo (
    @PrimaryKey
    private var id_interno: Long = 0,
    @SerializedName("_id")
    @ColumnInfo(name = "id_ciclo")
    val idCiclo: String? = null,
    val id_tabla: Long = 0,
    val ciclo: String,
    @SerializedName("fecha_inicio")
    @ColumnInfo(name = "fecha_inicio")
    val fechaInicio: Date,
    @SerializedName("fecha_siembra")
    @ColumnInfo(name = "fecha_siembra")
    val fechaSiembra: Date? = null,
    @SerializedName("fecha_fin_siembra")
    @ColumnInfo(name = "fecha_fin_siembra")
    val fechaFin_siembra: Date? = null,
    @SerializedName("fecha_fin")
    @ColumnInfo(name = "fecha_fin")
    val fechaFin: Date? = null,
    val editado: Boolean = false
)