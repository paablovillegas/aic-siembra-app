package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ranchos.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.Rancho
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoRepository

class FormRanchoViewModel(app: Application, idRancho: Long?) : AndroidViewModel(app) {
    private val ranchoRepository: RanchoRepository by lazy {
        RanchoRepository(app)
    }
    private val errorLD = MutableLiveData<String>()
    val error: LiveData<String> = errorLD
    val rancho: Rancho =
        if (idRancho == null) Rancho()
        else ranchoRepository.getRancho(idRancho)

    fun updateData(): Boolean {
        if (rancho.dataCorrect()) {
            ranchoRepository.insert(rancho.copy(editado = true))
            return true
        }
        errorLD.value = "Datos erróneos"
        return false
    }
}