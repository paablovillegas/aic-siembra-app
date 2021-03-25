package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.ranchos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.RanchoRegistroAdapter
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class ListaRanchosRegistros : Fragment() {
    private lateinit var viewModel: RegistrosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(requireActivity()).get(RegistrosViewModel::class.java)
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
        val ranchos = viewModel.getRanchos()
        if (ranchos.size == 1) {
            viewModel.idRancho = ranchos[0].rancho.id
            findNavController().navigate(R.id.action_lista_ranchos_registros_to_registros_fragment)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        val adapter = RanchoRegistroAdapter(ranchos, viewModel)
        recyclerView.adapter = adapter
    }
}