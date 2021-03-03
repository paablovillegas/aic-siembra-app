package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistenciaTrabajador.asistencia

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistenciaTrabajador.actividadTrabajador.ActividadTrabajador
import java.util.*

@Entity
data class Asistencia (
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    var idInterno: Long = 0,
    @SerializedName("_id")
    @ColumnInfo(name = "id_asistencia")
    var idAsistencia: String? = null,
    var entrada: Date,
    var salida: Date? = null,
    var editado: Boolean = false,
    @ColumnInfo(name = "id_trabajador")
    var trabajador: String? = null,
    @Ignore
    val actividades: List<ActividadTrabajador> = emptyList(),
)