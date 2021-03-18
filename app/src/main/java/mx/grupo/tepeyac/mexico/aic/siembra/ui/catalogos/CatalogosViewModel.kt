package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.CicloRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository

class CatalogosViewModel(app: Application) : AndroidViewModel(app) {
    private val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(app)
    }
    private val cicloRepository: CicloRepository by lazy {
        CicloRepository(app)
    }

    val catalogos: List<String> = listOf(
        "Ranchos",
        "Areas",
        "Grupos Trabajadores",
    )

    fun downloadRanchos() {
        ranchoRepository.downloadRanchos()
    }

    fun downloadCiclos() {
        cicloRepository.downloadCiclos()
    }

}