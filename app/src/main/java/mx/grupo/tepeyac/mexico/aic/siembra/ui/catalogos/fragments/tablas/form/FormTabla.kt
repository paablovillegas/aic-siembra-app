package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.tablas.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormTablaBinding

class FormTabla : Fragment() {
    private lateinit var binding: FormTablaBinding
    private lateinit var viewModel: FormTablaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = arguments?.getLong("id")
        val idRancho = arguments?.getLong("id_rancho")
        val factory = FormTablaViewModelFactory(requireActivity().application, idRancho!!, id)
        viewModel = ViewModelProvider(this, factory).get(FormTablaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormTablaBinding.inflate(inflater, container, false)
        binding.rancho = viewModel.rancho.rancho
        binding.tabla = viewModel.tabla
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        binding.formTablaRegistrar.setOnClickListener {
            if (viewModel.registrarTabla())
                findNavController().popBackStack()
        }
    }
}