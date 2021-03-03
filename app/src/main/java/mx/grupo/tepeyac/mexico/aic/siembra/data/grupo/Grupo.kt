package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

@Entity
data class Grupo(
    @PrimaryKey
    var id_interno: Long = 0,
    @ColumnInfo(name = "id_grupo")
    var idGrupo: String? = null,
    var grupo: String,
    @ColumnInfo(name = "tipo_grupo")
    var tipoGrupo: String,
    @ColumnInfo(name = "flete_regular")
    var fleteRegular: Double? = null,
    @ColumnInfo(name = "flete_persona")
    var fletePersona: Double? = null,
    @ColumnInfo(name = "flete_cabraliego")
    var fleteCabraliego: Double? = null,
    @ColumnInfo(name = "flete_incompleto")
    var fleteIncompleto: Double? = null,
    @ColumnInfo(name = "flete_escondida")
    var fleteEscondida: Double? = null,
)