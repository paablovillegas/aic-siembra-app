package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Cosecha(
    @PrimaryKey
    @ColumnInfo(name = "id_interno")
    var idInterno: Long = 0,
    var folio: String,
    //TODO: Cambiar
    var destino: String,
    //TODO: Cambiar
    var presentacion: String,
    @SerializedName("hora_salida")
    @ColumnInfo(name = "hora_salida")
    var horaSalida: Date = Date(),
    //TODO: Cambiar
    var transporte: String,
    //TODO: Cambiar
    var inocuidad: String,
    var tipo: String,
    //TODO: Cambiar
    var empaques: String,
    //TODO: Cambiar
    var cortes: String,
    //TODO: Cambiar
    var granel: String,
    var estatus: Int = 0,
)