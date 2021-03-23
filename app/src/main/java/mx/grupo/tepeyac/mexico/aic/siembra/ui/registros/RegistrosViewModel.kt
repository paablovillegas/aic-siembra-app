package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros

import androidx.lifecycle.ViewModel

class RegistrosViewModel : ViewModel() {
    val registros: List<String> = listOf(
        "Asistencias",
        "Actividades",
        "Extras",
        "Bonos",
        "Descuentos"
    )
}