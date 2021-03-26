package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.descuentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.DescuentoAdapter
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class ListaDescuentos : Fragment() {
    private lateinit var viewmodel2: RegistrosViewModel
    private lateinit var viewModel: ListaDescuentosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel2 = ViewModelProvider(requireActivity()).get(RegistrosViewModel::class.java)
        val factory = ListaDescuentosViewModelFactory(
            requireActivity().application,
            viewmodel2.idAsistenciaGrupo
        )
        viewModel = ViewModelProvider(this, factory)
            .get(ListaDescuentosViewModel::class.java)
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
        val adapter = DescuentoAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        viewModel.descuentos.observe(viewLifecycleOwner) {
            adapter.combineGrupos(it)
        }
    }
}