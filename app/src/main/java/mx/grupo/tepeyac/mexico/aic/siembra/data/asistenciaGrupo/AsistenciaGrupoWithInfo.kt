package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.ActividadTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.Asistencia
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.Bono
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento.Descuento
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.Extra

/*
 * @author pablovillegas
 * @version 1.5
 * @since 08/03/21
 */
data class AsistenciaGrupoWithInfo(
    @Embedded
    val asistenciaGrupo: AsistenciaGrupo,
    @Relation(
        parentColumn = "id_interno",
        entityColumn = "id_asistencia_grupo"
    )
    val asistencias: List<Asistencia>,
    @Relation(
        parentColumn = "id_interno",
        entityColumn = "id_asistencia_grupo"
    )
    val extras: List<Extra>,
    @Relation(
        parentColumn = "id_interno",
        entityColumn = "id_asistencia_grupo"
    )
    val bonos: List<Bono>,
    @Relation(
        parentColumn = "id_interno",
        entityColumn = "id_asistencia_grupo"
    )
    val descuento: List<Descuento>,
    @Relation(
        parentColumn = "id_interno",
        entityColumn = "id_asistencia_grupo"
    )
    val actividades: List<ActividadTrabajador>,
)