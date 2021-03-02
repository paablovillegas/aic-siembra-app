package mx.grupo.tepeyac.mexico.aic.siembra.data.actividadTrabajador

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ActividadTrabajador(
    @PrimaryKey
    var id_interno: Long = 0
)