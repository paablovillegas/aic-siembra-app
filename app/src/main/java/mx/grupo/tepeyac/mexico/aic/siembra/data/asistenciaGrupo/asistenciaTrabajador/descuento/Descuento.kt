package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistenciaTrabajador.descuento

import androidx.room.ColumnInfo

data class Descuento (
    @ColumnInfo(name = "id_interno")
    var idInterno: Long = 0,
    var cantidad: Double,
    var motivo: String
)