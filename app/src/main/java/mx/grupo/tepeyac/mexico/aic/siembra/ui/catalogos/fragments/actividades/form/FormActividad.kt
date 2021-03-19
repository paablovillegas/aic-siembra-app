package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.actividades.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormActividadBinding

class FormActividad : Fragment() {
    private lateinit var binding: FormActividadBinding
    private lateinit var viewModel: FormActividadViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idActividad = arguments?.getLong("id")
        val idArea = arguments?.getLong("id_area")
        val factory =
            FormActividadViewModelFactory(requireActivity().application, idArea!!, idActividad)
        viewModel = ViewModelProvider(this, factory).get(FormActividadViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormActividadBinding.inflate(inflater, container, false)
        binding.area = viewModel.area.area
        binding.actividad = viewModel.actividad
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        binding.formActividadRegistrar.setOnClickListener {
            if (viewModel.updateData())
                it.findNavController().popBackStack()
        }
    }
}