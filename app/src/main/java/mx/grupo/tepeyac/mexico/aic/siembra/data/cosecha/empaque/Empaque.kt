package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.empaque

import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey

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
)