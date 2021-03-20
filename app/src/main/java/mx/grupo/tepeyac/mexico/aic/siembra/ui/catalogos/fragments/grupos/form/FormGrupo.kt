package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.grupos.form

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
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormGrupoBinding

class FormGrupo : Fragment() {
    private lateinit var binding: FormGrupoBinding
    private lateinit var viewModel: FormGrupoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idGrupo = arguments?.getLong("id")
        val factory = FormGrupoViewModelFactory(requireActivity().application, idGrupo)
        viewModel = ViewModelProvider(this, factory).get(FormGrupoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormGrupoBinding.inflate(inflater, container, false)
        binding.grupo = viewModel.grupo
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            viewModel.tiposGrupo,
        )
        val dropDownTipoGrupo: AutoCompleteTextView? =
            binding.selectTipoGrupo.editText as? AutoCompleteTextView
        dropDownTipoGrupo?.setText(viewModel.grupo.tipoGrupo, false)
        dropDownTipoGrupo?.setAdapter(adapter)
        dropDownTipoGrupo?.setOnItemClickListener { _, _, position, _ ->
            viewModel.grupo.tipoGrupo = viewModel.tiposGrupo[position]
        }

        binding.formGrupoRegistrar.setOnClickListener {
            if (viewModel.updateData())
                it.findNavController().popBackStack()
        }
    }
}