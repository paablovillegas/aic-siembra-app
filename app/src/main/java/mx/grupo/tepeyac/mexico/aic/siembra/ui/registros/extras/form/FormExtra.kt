package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.extras.form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ListaSubmitBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class FormExtra : Fragment() {
    lateinit var binding: ListaSubmitBinding
    lateinit var viewModel: RegistrosViewModel
    lateinit var viewModelAct: FormExtraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RegistrosViewModel::class.java)
        val factory = FormExtraViewModelFactory(
            requireActivity().application,
            viewModel.idAsistenciaGrupo,
        )
        viewModelAct = ViewModelProvider(requireActivity(), factory)
            .get(FormExtraViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListaSubmitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveTrabajadoresIds.observe(viewLifecycleOwner) {
            Log.i("TAG", "onViewCreated: $it")
        }
    }

}