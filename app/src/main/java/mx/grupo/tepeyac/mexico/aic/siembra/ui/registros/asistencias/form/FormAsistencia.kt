package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.asistencias.form

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormAsistenciaBinding
import java.util.*

class FormAsistencia : Fragment() {
    lateinit var binding: FormAsistenciaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormAsistenciaBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setTime(date: Date) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        val dialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                //TODO: Set Field
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        dialog.show()
    }

}