package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.TablaItem

@Entity
data class Tabla(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_tabla")
    var idTabla: String? = null,
    var tabla: String,
    var editado: Boolean = false,
    @ColumnInfo(name = "id_rancho")
    var idRancho: String?,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idTabla: String? = null,
        tabla: String,
        editado: Boolean = false,
        idRancho: String?,
    ) : this(id, idTabla, tabla, editado, idRancho, false)

    fun toTablaItem(): TablaItem = TablaItem(idTabla!!, tabla)
}