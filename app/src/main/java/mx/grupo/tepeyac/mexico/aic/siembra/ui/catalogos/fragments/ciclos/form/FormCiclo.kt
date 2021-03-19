package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ciclos.form

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
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormCicloBinding

class FormCiclo : Fragment() {
    private lateinit var binding: FormCicloBinding
    private lateinit var viewModel: FormCicloViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getLong("id")
        val idTabla = arguments?.getLong("id_tabla")
        val factory = FormCicloViewModelFactory(requireActivity().application, idTabla!!, id)
        viewModel = ViewModelProvider(this, factory).get(FormCicloViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormCicloBinding.inflate(inflater, container, false)
        binding.rancho = viewModel.rancho.rancho
        binding.tabla = viewModel.tabla.tabla
        binding.ciclo = viewModel.ciclo
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
            viewModel.productosString
        )
        val producto: AutoCompleteTextView? =
            (binding.selectProducto.editText as? AutoCompleteTextView)
        producto?.setAdapter(adapter)
        producto?.setText(viewModel.getProducto(), false)
        producto?.setOnItemClickListener { _, _, position, _ ->
            viewModel.setProducto(position)
        }

        binding.formCicloRegistrar.setOnClickListener {
            Log.i("TAG", "onViewCreated: ${viewModel.ciclo}")
            if (viewModel.registrarCiclo())
                findNavController().popBackStack()
        }
    }
}