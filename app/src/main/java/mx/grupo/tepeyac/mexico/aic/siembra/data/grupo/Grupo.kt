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
    var grupo: String = "",
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

    fun dataCorrect(): Boolean =
        grupo.isNotEmpty()

    fun normalize(): Grupo =
        this.copy(
            fleteIncompleto = if (fleteIncompleto == 0.0) null else fleteIncompleto,
            fleteRegular = if (fleteRegular == 0.0) null else fleteRegular,
            fleteCabraliego = if (fleteCabraliego == 0.0) null else fleteCabraliego,
            fleteEscondida = if (fleteEscondida == 0.0) null else fleteEscondida,
            fletePersona = if (fletePersona == 0.0) null else fletePersona,
        )
}