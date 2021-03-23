package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.Asistencia
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.Grupo
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.Rancho

data class AsistenciaGrupoWithAsistencias(
    @Embedded
    val asistenciaGrupo: AsistenciaGrupo,
    @Relation(
        parentColumn = "id_grupo",
        entityColumn = "id_interno",
    )
    val grupo: Grupo,
    @Relation(
        parentColumn = "id_rancho",
        entityColumn = "id_interno",
    )
    val rancho: Rancho,
    @Relation(
        parentColumn = "id_interno",
        entityColumn = "id_asistencia_grupo"
    )
    val asistencias: List<Asistencia>,
)
