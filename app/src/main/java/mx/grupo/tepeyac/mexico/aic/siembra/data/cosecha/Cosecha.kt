package mx.grupo.tepeyac.mexico.aic.siembra.data.cosecha

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Cosecha(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_cosecha")
    val idCosecha: String? = null,
    val folio: Long,
    val fecha: Date = Date(),
    val tipo: String,
    val estatus: Int = 0,
)
/*
    //TODO: Cambiar
    val destino: String,
    //TODO: Cambiar
    val presentacion: String,
    //TODO: Cambiar
    val transporte: String,
    //TODO: Cambiar
    val inocuidad: String,
    val empaques: String,
    //TODO: Cambiar
    val cortes: String,
    //TODO: Cambiar
    val granel: String,
 */