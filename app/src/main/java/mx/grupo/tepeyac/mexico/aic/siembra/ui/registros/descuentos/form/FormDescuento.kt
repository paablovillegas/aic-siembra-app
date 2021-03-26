package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.descuentos.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormDescuentoBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class FormDescuento : Fragment() {
    lateinit var binding: FormDescuentoBinding
    lateinit var viewModel: RegistrosViewModel
    lateinit var viewModelDesc: FormDescuentoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RegistrosViewModel::class.java)
        val id: Long? = arguments?.getLong("id")
        val factory = FormDescuentoViewModelFactory(
            requireActivity().application,
            viewModel.idAsistenciaGrupo,
            id
        )
        viewModelDesc = ViewModelProvider(this, factory)
            .get(FormDescuentoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormDescuentoBinding.inflate(inflater, container, false)
        binding.descuento = viewModelDesc.descuento
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectTrabajador: AutoCompleteTextView =
            binding.selectTrabajador.editText as AutoCompleteTextView
        selectTrabajador.setText(viewModelDesc.getTrabajador(), false)
        viewModelDesc.trabajadores.observe(viewLifecycleOwner) { trabajadores ->
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                trabajadores.map { it.getNombreCompleto() }
            )
            selectTrabajador.setAdapter(adapter)
            selectTrabajador.setOnItemClickListener { _, _, i, _ ->
                viewModelDesc.setTrabajador(trabajadores[i].id)
            }
        }
        viewModelDesc.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        binding.formCicloRegistrar.setOnClickListener {
            if (viewModelDesc.registrar())
                findNavController().popBackStack()
        }
    }
}