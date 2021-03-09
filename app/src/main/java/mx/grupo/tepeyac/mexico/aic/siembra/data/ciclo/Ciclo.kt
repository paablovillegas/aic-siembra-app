package mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Ciclo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    val id: Long = 0,
    @ColumnInfo(name = "id_ciclo")
    val idCiclo: String? = null,
    val ciclo: String,
    @ColumnInfo(name = "id_tabla")
    val idTabla: Long = 0,
    @ColumnInfo(name = "id_producto")
    val idProducto: Long = 0,
    @ColumnInfo(name = "fecha_inicio")
    val fechaInicio: Date,
    @ColumnInfo(name = "fecha_siembra")
    val fechaSiembra: Date? = null,
    @ColumnInfo(name = "fecha_cosecha")
    val fechaCosecha: Date? = null,
    @ColumnInfo(name = "fecha_fin")
    val fechaFin: Date? = null,
    val editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idCiclo: String? = null,
        ciclo: String,
        idTabla: Long,
        idProducto: Long,
        fechaInicio: Date,
        fechaSiembra: Date? = null,
        fechaCosecha: Date? = null,
        fechaFin: Date? = null,
        editado: Boolean = false,
    ) : this(
        id,
        idCiclo,
        ciclo,
        idTabla,
        idProducto,
        fechaInicio,
        fechaSiembra,
        fechaCosecha,
        fechaFin,
        editado,
        false
    )

    fun toSendCicloItem(idTabla: String, idProducto: String): SendCicloItem =
        SendCicloItem(
            idCiclo,
            ciclo,
            idTabla,
            idProducto,
            fechaInicio,
            fechaSiembra,
            fechaCosecha,
            fechaFin,
        )
}