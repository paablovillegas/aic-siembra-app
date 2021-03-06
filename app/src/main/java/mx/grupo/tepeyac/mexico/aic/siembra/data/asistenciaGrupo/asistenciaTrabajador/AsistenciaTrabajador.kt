package mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistenciaTrabajador

import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.Asistencia
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.Bono
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento.Descuento
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.Extra
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador

data class AsistenciaTrabajador(
    val trabajador: Trabajador,
    val asistencia: Asistencia,
    val extras: List<Extra> = emptyList(),
    val bonos: List<Bono> = emptyList(),
    val descuentos: List<Descuento> = emptyList(),
)