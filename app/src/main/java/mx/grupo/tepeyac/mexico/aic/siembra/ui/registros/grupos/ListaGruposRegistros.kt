package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.grupos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.*
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class ListaGruposRegistros : Fragment() {
    private lateinit var viewModelFechas: RegistrosViewModel
    private lateinit var viewModel: ListaGruposRegistrosViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type: Int? = arguments?.getInt("id")
        val factory = ListaGruposRegistrosViewModelFactory(requireActivity().application, type)
        viewModel = ViewModelProvider(this, factory)
            .get(ListaGruposRegistrosViewModel::class.java)
        viewModelFechas = ViewModelProvider(requireActivity()).get(RegistrosViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.lista_general)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        when (viewModel.type) {
            0 -> bindAsistencias()
            1 -> bindActividades()
            2 -> bindExtras()
            3 -> bindBonos()
            4 -> bindDescuentos()
        }
    }

    fun bindAsistencias() {
        val adapter = GrupoAsistenciasAdapter(viewModelFechas)
        recyclerView.adapter = adapter
        viewModel.getAsistencias(
            viewModelFechas.startDate,
            viewModelFechas.endDate,
        ).observe(viewLifecycleOwner) {
            adapter.combineGrupos(it)
        }
    }

    fun bindActividades() {
        val adapter = GrupoActividadesAdapter(viewModelFechas)
        recyclerView.adapter = adapter
        viewModel.getActividades(
            viewModelFechas.startDate,
            viewModelFechas.endDate,
        ).observe(viewLifecycleOwner) {
            adapter.combineGrupos(it)
        }
    }

    fun bindExtras() {
        val adapter = GrupoExtrasAdapter(viewModelFechas)
        recyclerView.adapter = adapter
        viewModel.getExtras(
            viewModelFechas.startDate,
            viewModelFechas.endDate,
        ).observe(viewLifecycleOwner) {
            adapter.combineGrupos(it)
        }
    }

    fun bindBonos() {
        val adapter = GrupoBonosAdapter(viewModelFechas)
        recyclerView.adapter = adapter
        viewModel.getBonos(
            viewModelFechas.startDate,
            viewModelFechas.endDate,
        ).observe(viewLifecycleOwner) {
            adapter.combineGrupos(it)
        }
    }

    fun bindDescuentos() {
        val adapter = GrupoDescuentosAdapter(viewModelFechas)
        recyclerView.adapter = adapter
        viewModel.getDescuentos(
            viewModelFechas.startDate,
            viewModelFechas.endDate,
        ).observe(viewLifecycleOwner) {
            adapter.combineGrupos(it)
        }
    }
}