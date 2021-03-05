package mx.grupo.tepeyac.mexico.aic.siembra.data.rancho

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rancho(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_interno")
    var id: Long = 0,
    @ColumnInfo(name = "id_rancho")
    var idRancho: String?,
    var rancho: String,
    var alias: String?,
    var editado: Boolean = false,
) {
    fun toRanchoItem(tablas: List<TablaItem>): RanchoItem? =
        when (idRancho) {
            null -> null
            else -> RanchoItem(idRancho!!, rancho, alias, tablas)
        }
}