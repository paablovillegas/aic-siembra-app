package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.corte

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Corte(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_corte")
    val idCorte: String,
    @ColumnInfo(name = "id_cosecha")
    val idCosecha: String,
    @ColumnInfo(name = "id_ciclo")
    val idCiclo: String,
    @ColumnInfo(name = "id_cuadrilla")
    val idCuadrilla: String,
    val colotes: Int,
    val flete: Double,
    val capitan: Double,
    val apuntador: Double,
    val resagador: Double,
    val editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idCorte: String,
        idCosecha: String,
        idCiclo: String,
        idCuadrilla: String,
        colotes: Int,
        flete: Double,
        capitan: Double,
        apuntador: Double,
        resagador: Double,
        editado: Boolean = false,
    ) : this(
        id,
        idCorte,
        idCosecha,
        idCiclo,
        idCuadrilla,
        colotes,
        flete,
        capitan,
        apuntador,
        resagador,
        editado,
        false
    )
}