package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.bonos.form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.TrabajadorBonoAdapter
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ListaSubmitBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class FormBono : Fragment() {
    lateinit var binding: ListaSubmitBinding
    lateinit var viewModel: RegistrosViewModel
    lateinit var viewModelAct: FormBonoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RegistrosViewModel::class.java)
        val factory = FormBonoViewModelFactory(
            viewModel.getTrabajadores(),
            viewModel.idAsistenciaGrupo,
        )
        viewModelAct = ViewModelProvider(requireActivity(), factory)
            .get(FormBonoViewModel::class.java)
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

        val adapter = TrabajadorBonoAdapter()
        adapter.combineListas(viewModelAct.bonos)
        binding.listaGeneral.setHasFixedSize(true)
        binding.listaGeneral.layoutManager = LinearLayoutManager(requireContext())
        binding.listaGeneral.adapter = adapter
        binding.listaGeneral.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL,
            )
        )

        binding.listaRegistrar.setOnClickListener {
            Log.i("TAG", "onViewCreated: ${viewModelAct.bonos}")
            //TODO: registrar
            findNavController().popBackStack(R.id.lista_grupos_registros, false)
        }
    }

}