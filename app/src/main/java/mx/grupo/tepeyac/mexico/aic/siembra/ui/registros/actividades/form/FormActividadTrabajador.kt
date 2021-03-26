package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.actividades.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormActividadTrabajadorBinding

class FormActividadTrabajador : Fragment() {
    lateinit var binding: FormActividadTrabajadorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormActividadTrabajadorBinding.inflate(inflater, container, false)
        return binding.root
    }

}