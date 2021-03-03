package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistenciaTrabajador.AsistenciaTrabajador
import java.util.*

@Entity
data class AsistenciaGrupo(
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    var idInterno: Long = 0,
    @SerializedName("_id")
    @ColumnInfo(name = "id_asistencia_grupo")
    var idAsistenciaGrupo: String? = null,
    @ColumnInfo(name = "id_rancho")
    var rancho: String,
    var flete: Double,
    var fecha: Date,
    @Ignore
    var trabajadores: List<AsistenciaTrabajador> = emptyList()
)