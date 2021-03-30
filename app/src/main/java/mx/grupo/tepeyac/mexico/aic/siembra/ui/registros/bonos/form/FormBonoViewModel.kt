package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.bonos.form

import androidx.lifecycle.ViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.Bono
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.BonoWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import java.util.*

class FormBonoViewModel(
    trabajadores: List<Trabajador>,
    idAsistenciaGrupo: Long,
) : ViewModel() {
    var bonos: List<BonoWithTrabajador> = trabajadores.map {
        BonoWithTrabajador(
            Bono(
                total = 0.0,
                idTrabajador = it.id,
                idAsistenciaGrupo = idAsistenciaGrupo,
                fecha = Date()
            ),
            it
        )
    }
}