package mx.grupo.tepeyac.mexico.aic.siembra.data.area

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Area(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_area")
    var idArea: String? = null,
    var area: String = "",
    var editado: Boolean = false,
    @Ignore
    val delete: Boolean = false,
) {
    constructor(
        id: Long = 0,
        idArea: String? = null,
        area: String,
        editado: Boolean = false
    ) : this(id, idArea, area, editado, false)

    fun dataCorrect(): Boolean =
        area.isNotEmpty()
}