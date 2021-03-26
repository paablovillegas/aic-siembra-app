package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.asistencias.form

import android.R
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormAsistenciaBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel
import java.text.SimpleDateFormat
import java.util.*

class FormAsistencia : Fragment() {
    private lateinit var binding: FormAsistenciaBinding
    private lateinit var viewModel: RegistrosViewModel
    private lateinit var viewModelAs: FormAsistenciaViewModel
    private val format = SimpleDateFormat("HH:mm", Locale.forLanguageTag("es-MX"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RegistrosViewModel::class.java)
        val id = arguments?.getLong("id")
        val factory = FormAsistenciaViewModelFactory(
            requireActivity().application,
            viewModel.idAsistenciaGrupo,
            id
        )
        viewModelAs = ViewModelProvider(this, factory)
            .get(FormAsistenciaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormAsistenciaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectTrabajador: AutoCompleteTextView =
            binding.selectTrabajador.editText as AutoCompleteTextView
        selectTrabajador.setText(viewModelAs.getTrabajador(), false)
        viewModelAs.trabajadores.observe(viewLifecycleOwner) { trabajadores ->
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                trabajadores.map { it.getNombreCompleto() }
            )
            selectTrabajador.setAdapter(adapter)
            selectTrabajador.setOnItemClickListener { _, _, i, _ ->
                viewModelAs.setTrabajador(trabajadores[i].id)
            }
        }
        viewModelAs.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        binding.asistenciaEntrada.setOnClickListener {
            setTime(viewModelAs.asistencia.entrada, 1)
        }
        binding.asistenciaSalida.setOnClickListener {
            setTime(viewModelAs.asistencia.salida ?: kotlin.run { Date() }, 2)
        }
        binding.formCicloRegistrar.setOnClickListener {
            Log.i("TAG", "onViewCreated: ${viewModelAs.asistencia}")
            if (viewModelAs.registrar())
                findNavController().popBackStack()
        }
        viewModelAs.asistencia.let { asis ->
            binding.asistenciaEntrada.setText(format.format(asis.entrada))
            viewModelAs.asistencia.salida?.let {
                binding.asistenciaSalida.setText(format.format(it))
            }
        }
    }

    private fun setTime(date: Date, field: Int) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        val dialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                if (field == 1) {
                    viewModelAs.asistencia.entrada = calendar.time
                    binding.asistenciaEntrada.setText(format.format(calendar.time))
                } else {
                    viewModelAs.asistencia.salida = calendar.time
                    binding.asistenciaSalida.setText(format.format(calendar.time))
                }
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        dialog.show()
    }

}