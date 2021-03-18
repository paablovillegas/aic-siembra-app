package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ranchos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.RanchoAdapter

class ListaRanchos : Fragment() {

    private lateinit var viewModel: ListaRanchosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListaRanchosViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.lista_general)
        val adapter = RanchoAdapter()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel.ranchos.observe(viewLifecycleOwner) {
            adapter.combineRanchos(it)
        }
    }
}