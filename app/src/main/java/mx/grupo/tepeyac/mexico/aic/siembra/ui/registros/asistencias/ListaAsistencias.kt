package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.asistencias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.AsistenciaAdapter
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class ListaAsistencias : Fragment() {
    private lateinit var viewModel: ListaAsistenciasViewModel
    private lateinit var viewModelG: RegistrosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelG = ViewModelProvider(requireActivity()).get(RegistrosViewModel::class.java)
        val factory = ListaAsistenciasViewModelFactory(
            requireActivity().application,
            viewModelG.idAsistenciaGrupo
        )
        viewModel = ViewModelProvider(this, factory)
            .get(ListaAsistenciasViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.lista_general)
        val adapter = AsistenciaAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        viewModel.asistencias.observe(viewLifecycleOwner) {
            adapter.combineGrupos(it)
        }
    }
}