package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.extras.form

import androidx.lifecycle.ViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.Extra
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.ExtraWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import java.util.*

class FormExtraViewModel(
    trabajadores: List<Trabajador>,
    idAsistenciaGrupo: Long,
) : ViewModel() {
    var extras: List<ExtraWithTrabajador> = trabajadores.map {
        ExtraWithTrabajador(
            Extra(
                horas = 0,
                total = 0.0,
                idTrabajador = it.id,
                idAsistenciaGrupo = idAsistenciaGrupo,
                fecha = Date()
            ),
            it
        )
    }
}