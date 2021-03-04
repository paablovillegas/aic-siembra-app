package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha.transporte

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
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
) {
    constructor(
        id: Long = 0,
        idTransporte: String? = null,
        chofer: String,
        placas: String,
        celular: String,
        modeloVehiculo: String,
        tipo: String,
        editado: Boolean = false,
        idCosecha: String,
    ) : this(
        id,
        idTransporte,
        chofer,
        placas,
        celular,
        modeloVehiculo,
        tipo,
        editado,
        idCosecha,
        false
    )
}