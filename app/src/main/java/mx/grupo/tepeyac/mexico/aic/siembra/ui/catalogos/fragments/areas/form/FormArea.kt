package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.areas.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormAreaBinding

class FormArea : Fragment() {
    private lateinit var binding: FormAreaBinding
    private lateinit var viewModel: FormAreaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idArea = arguments?.getLong("id")
        val factory = FormAreaViewModelFactory(requireActivity().application, idArea)
        viewModel = ViewModelProvider(this, factory).get(FormAreaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormAreaBinding.inflate(inflater, container, false)
        binding.area = viewModel.area
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        binding.formAreaRegistrar.setOnClickListener {
            if (viewModel.updateData())
                it.findNavController().popBackStack()
        }
    }
}