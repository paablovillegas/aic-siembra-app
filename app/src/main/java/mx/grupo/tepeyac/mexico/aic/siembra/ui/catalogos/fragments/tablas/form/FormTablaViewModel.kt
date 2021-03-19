package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.tablas.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.Rancho
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla

class FormTablaViewModel(
    app: Application,
    idRancho: Long,
    idTabla: Long?
) : AndroidViewModel(app) {
    private val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(app)
    }
    private val errorLD = MutableLiveData<String>()
    val error: LiveData<String> = errorLD
    val rancho: Rancho = ranchoRepository.getRancho(idRancho)
    val tabla: Tabla =
        if (idTabla == null || idTabla == 0L) Tabla(idRancho = idRancho)
        else ranchoRepository.getTabla(idTabla)

    fun registrarTabla(): Boolean {
        if (tabla.dataCorrect())
            ranchoRepository.insert(tabla.copy(editado = true))
        else
            errorLD.value = "Datos erróneos"
        return tabla.dataCorrect()
    }
}