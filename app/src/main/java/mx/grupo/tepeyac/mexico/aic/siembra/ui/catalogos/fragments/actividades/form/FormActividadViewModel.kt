package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.actividades.form

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.Area
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaRepository
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad

class FormActividadViewModel(app: Application, idArea: Long, idActividad: Long?) :
    AndroidViewModel(app) {
    private val areaRepository: AreaRepository by lazy {
        AreaRepository(app)
    }

    private val errorLD = MutableLiveData<String>()
    val error: LiveData<String> = errorLD
    val area: Area = areaRepository.getArea(idArea)
    val actividad: Actividad =
        if (idActividad == null || idActividad == 0L) Actividad(idArea = idArea)
        else areaRepository.getActividad(idActividad)

    fun updateData(): Boolean {
        if (actividad.dataCorrect())
            areaRepository.insert(actividad)
        else
            errorLD.value = "Datos Erróneos"
        return actividad.dataCorrect()
    }
}