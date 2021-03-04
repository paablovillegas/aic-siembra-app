package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.empaque

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Empaque(
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
    val cajas: Int,
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
        cajas: Int,
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
        cajas,
        flete,
        capitan,
        apuntador,
        resagador,
        editado,
        false
    )
}