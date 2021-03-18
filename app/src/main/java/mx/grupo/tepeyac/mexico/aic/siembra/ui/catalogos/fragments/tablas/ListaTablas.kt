package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.tablas

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
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.TablaAdapter

class ListaTablas : Fragment() {
    private lateinit var viewModel: ListaTablasViewModel

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
        val factory = ListaTablasViewModelFactory(requireActivity().application, id!!)
        viewModel = ViewModelProvider(this, factory).get(ListaTablasViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.lista_general)
        val adapter = TablaAdapter()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel.rancho.observe(viewLifecycleOwner) {
            adapter.combineTablas(it.tablas)
        }
    }
}