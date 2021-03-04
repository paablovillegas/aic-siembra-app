package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.transporte

import androidx.room.ColumnInfo
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class Transporte(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_transporte")
    val idTransporte: String? = null,
    val chofer: String,
    val placas: String,
    val celular: String,
    @ColumnInfo(name = "modelo_vehiculo")
    val modeloVehiculo: String,
    val tipo: String,
    val editado: Boolean = false,
    val idCosecha: String,
    @Ignore
    val delete: Boolean = false,
)