package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Grupo(
    @PrimaryKey
    var id_interno: Long = 0,
    var id_grupo: String? = null,
    var grupo: String,
    var tipo_grupo: String,
    var flete_regular: Double? = null,
    var flete_persona: Double? = null
)