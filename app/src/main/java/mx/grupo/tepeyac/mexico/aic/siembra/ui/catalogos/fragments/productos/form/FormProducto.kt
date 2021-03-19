package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.productos.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormProductoBinding

class FormProducto : Fragment() {
    private lateinit var binding: FormProductoBinding
    private lateinit var viewModel: FormProductoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idProducto = arguments?.getLong("id")
        val factory = FormProductoViewModelFactory(requireActivity().application, idProducto)
        viewModel = ViewModelProvider(this, factory).get(FormProductoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormProductoBinding.inflate(inflater, container, false)
        binding.producto = viewModel.producto
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        binding.formProductoRegistrar.setOnClickListener {
            if (viewModel.updateData())
                it.findNavController().popBackStack()
        }
    }
}