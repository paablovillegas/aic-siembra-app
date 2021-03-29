package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.seleccion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.detailsLookup.ActividadDetailsLookup
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.SelectTablaAdapter
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class ListaTablasRegistros : Fragment() {
    private lateinit var viewModel: RegistrosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RegistrosViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler: RecyclerView = view.findViewById(R.id.lista_general)
        val adapter = SelectTablaAdapter()
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        val tracker: SelectionTracker<Long> = SelectionTracker
            .Builder(
                "actividadActividad",
                recycler,
                StableIdKeyProvider(recycler),
                ActividadDetailsLookup(recycler),
                StorageStrategy.createLongStorage()
            )
            .withSelectionPredicate(SelectionPredicates.createSelectAnything())
            .build()
        adapter.tracker = tracker
        tracker.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onItemStateChanged(key: Long, selected: Boolean) {
                super.onItemStateChanged(key, selected)
                viewModel.setTablas(tracker.selection.map { it })
            }
        })
        viewModel.liveTablasIds.value?.let {
            tracker.setItemsSelected(it, true)
        }
        viewModel.getTablas().observe(viewLifecycleOwner) {
            adapter.combineActividades(it)
        }
    }
}