package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.trabajadores.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormTrabajadorBinding

class FormTrabajador : Fragment() {
    private lateinit var binding: FormTrabajadorBinding
    private lateinit var viewModel: FormTrabajadorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idTrabajador = arguments?.getLong("id")
        val idGrupo = arguments?.getLong("id_grupo")
        val factory =
            FormTrabajadorViewModelFactory(requireActivity().application, idGrupo!!, idTrabajador)
        viewModel = ViewModelProvider(this, factory).get(FormTrabajadorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormTrabajadorBinding.inflate(inflater, container, false)
        binding.grupo = viewModel.grupo.grupo
        binding.trabajador = viewModel.trabajador
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            viewModel.generos
        )
        val selectGenero = binding.selectGenero.editText as AutoCompleteTextView
        selectGenero.setAdapter(adapter)
        selectGenero.setOnItemClickListener { _, _, position, _ ->
            viewModel.setGenero(position)
        }
        viewModel.trabajador.genero?.let {
            viewModel.trabajador.genero = it
            selectGenero.setText(it, false)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        binding.formTrabajadorRegistrar.setOnClickListener {
            if (viewModel.updateData())
                it.findNavController().popBackStack()
        }
    }
}