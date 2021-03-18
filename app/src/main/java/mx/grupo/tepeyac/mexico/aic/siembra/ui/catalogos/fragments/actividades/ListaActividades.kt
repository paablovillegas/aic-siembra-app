package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.actividades

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.ActividadAdapter

class ListaActividades : Fragment() {
    private lateinit var viewModel: ListaActividadesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id: Long? = arguments?.getLong("id")
        if (id == null) {
            this.findNavController().popBackStack()
        }
        val factory = ListaActividadesViewModelFactory(requireActivity().application, id!!)
        viewModel = ViewModelProvider(this, factory).get(ListaActividadesViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.lista_general)
        val adapter = ActividadAdapter()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel.area.observe(viewLifecycleOwner) {
            adapter.combineActividades(it.actividades)
        }
    }
}