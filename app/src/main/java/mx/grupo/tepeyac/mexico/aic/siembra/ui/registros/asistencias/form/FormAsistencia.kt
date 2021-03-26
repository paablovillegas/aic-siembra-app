package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.asistencias.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormAsistenciaBinding

class FormAsistencia : Fragment() {
    lateinit var binding: FormAsistenciaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormAsistenciaBinding.inflate(inflater, container, false)
        return binding.root
    }

}