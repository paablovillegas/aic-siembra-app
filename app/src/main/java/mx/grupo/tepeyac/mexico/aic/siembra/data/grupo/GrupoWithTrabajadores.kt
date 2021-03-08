package mx.grupo.tepeyac.mexico.aic.siembra.data.grupo

import androidx.room.Embedded
import androidx.room.Relation
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

data class GrupoWithTrabajadores(
    @Embedded
    val grupo: Grupo,
    @Relation(
        parentColumn = "id_interno",
        entityColumn = "id_grupo",
    )
    val trabajadores: List<Trabajador>
) {
    fun toSendGrupoItem(): SendGrupoItem =
        SendGrupoItem(
            grupo.idGrupo,
            grupo.grupo,
            getFletes(),
            grupo.tipoGrupo,
            trabajadores.mapNotNull { it.idTrabajador }
        )

    fun getFletes(): ResponseFlete =
        ResponseFlete(
            grupo.fleteRegular,
            grupo.fletePersona,
            grupo.fleteCabraliego,
            grupo.fleteIncompleto,
            grupo.fleteEscondida
        )
}