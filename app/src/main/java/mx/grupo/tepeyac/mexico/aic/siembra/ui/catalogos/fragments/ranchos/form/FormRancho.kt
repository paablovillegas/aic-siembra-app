package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ranchos.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormRanchoBinding

class FormRancho : Fragment() {
    private lateinit var binding: FormRanchoBinding
    private lateinit var viewModel: FormRanchoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idRancho = arguments?.getLong("id")
        val factory = FromRanchoViewModelFactory(requireActivity().application, idRancho)
        viewModel = ViewModelProvider(this, factory).get(FormRanchoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormRanchoBinding.inflate(inflater, container, false)
        binding.rancho = viewModel.rancho
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        binding.formRanchoRegistrar.setOnClickListener {
            if (viewModel.updateData())
                it.findNavController().popBackStack()
        }
    }
}