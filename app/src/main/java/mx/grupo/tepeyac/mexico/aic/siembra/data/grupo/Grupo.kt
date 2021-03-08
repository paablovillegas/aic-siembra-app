package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Grupo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_grupo")
    val idGrupo: String? = null,
    val grupo: String,
    @ColumnInfo(name = "tipo_grupo")
    val tipoGrupo: String,
    @ColumnInfo(name = "flete_regular")
    val fleteRegular: Double? = null,
    @ColumnInfo(name = "flete_persona")
    val fletePersona: Double? = null,
    @ColumnInfo(name = "flete_cabraliego")
    val fleteCabraliego: Double? = null,
    @ColumnInfo(name = "flete_incompleto")
    val fleteIncompleto: Double? = null,
    @ColumnInfo(name = "flete_escondida")
    val fleteEscondida: Double? = null,
    val editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idGrupo: String? = null,
        grupo: String,
        tipoGrupo: String,
        fleteRegular: Double? = null,
        fletePersona: Double? = null,
        fleteCabraliego: Double? = null,
        fleteIncompleto: Double? = null,
        fleteEscondida: Double? = null,
        editado: Boolean = false,
    ) : this(
        id,
        idGrupo,
        grupo,
        tipoGrupo,
        fleteRegular,
        fletePersona,
        fleteCabraliego,
        fleteIncompleto,
        fleteEscondida,
        editado,
        false,
    )
}