package mx.grupo.tepeyac.mexico.aic.siembra.ui.catalogos.fragments.ciclos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgument
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.CicloAdapter

class ListaCiclos : Fragment() {
    private lateinit var viewModel: ListaCiclosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id: Long? = arguments?.getLong("id")
        val argument1 = NavArgument.Builder().setDefaultValue(id).build()
        findNavController().currentDestination?.addArgument("id_tabla", argument1)
        val factory = ListaCiclosViewModelFactory(requireActivity().application, id!!)
        viewModel = ViewModelProvider(this, factory).get(ListaCiclosViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.lista_general)
        val adapter = CicloAdapter()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel.ciclos.observe(viewLifecycleOwner) {
            adapter.combineCiclos(it)
        }
    }
}