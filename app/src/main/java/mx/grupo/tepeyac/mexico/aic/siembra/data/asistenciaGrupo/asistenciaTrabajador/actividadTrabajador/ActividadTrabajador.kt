package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistenciaTrabajador.actividadTrabajador

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla

@Entity
data class ActividadTrabajador(
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    var idInterno: Long = 0,
    @SerializedName("_id")
    @ColumnInfo(name = "id_actividad_trabajador")
    var idActividadTrabajador: String? = null,
    var actividad: String,
    @Ignore
    var tablas: List<Tabla> = emptyList(),
)