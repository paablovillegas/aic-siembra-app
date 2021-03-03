package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

data class GrupoWithTrabajadores(
    @Embedded
    val grupo: Grupo,
    @Relation(
        parentColumn = "id_grupo",
        entityColumn = "id_grupo",
    )
    val trabajadores: List<Trabajador>
)