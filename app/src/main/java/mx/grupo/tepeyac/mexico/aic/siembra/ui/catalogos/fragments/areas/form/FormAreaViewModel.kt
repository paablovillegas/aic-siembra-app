package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.areas.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.Area
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaRepository

class FormAreaViewModel(app: Application, id: Long?) : AndroidViewModel(app) {
    private val areaRepository: AreaRepository by lazy {
        AreaRepository(app)
    }

    private val errorLD = MutableLiveData<String>()
    val error: LiveData<String> = errorLD
    val area: Area =
        if (id == null || id == 0L) Area()
        else areaRepository.getArea(id)

    fun updateData(): Boolean {
        if (area.dataCorrect())
            areaRepository.insert(area)
        else
            errorLD.value = "Datos Erróneos"
        return area.dataCorrect()
    }
}