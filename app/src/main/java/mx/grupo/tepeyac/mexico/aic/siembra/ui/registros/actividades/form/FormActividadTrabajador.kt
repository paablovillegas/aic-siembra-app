package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.actividades.form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.SelectedActividadAdapter
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.SelectedTablaAdapter
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView.SelectedTrabajadorAdapter
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.FormActividadTrabajadorBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class FormActividadTrabajador : Fragment() {
    lateinit var binding: FormActividadTrabajadorBinding
    lateinit var viewModel: RegistrosViewModel
    lateinit var viewModelAct: FormActividadTrabajadorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RegistrosViewModel::class.java)
        val factory = FromActividadTrabajadorViewModelFactory(
            requireActivity().application,
            viewModel.idAsistenciaGrupo,
        )
        viewModelAct = ViewModelProvider(requireActivity(), factory)
            .get(FormActividadTrabajadorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FormActividadTrabajadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterActividades = SelectedActividadAdapter(viewModel.getActividades())
        val adapterTablas = SelectedTablaAdapter(viewModel.getTablas())
        val adapterTrabajadores = SelectedTrabajadorAdapter(viewModel.getTrabajadores())
        viewModel.liveActividadesIds.observe(viewLifecycleOwner) {
            adapterActividades.setSelection(it)
        }
        viewModel.liveTablasIds.observe(viewLifecycleOwner) {
            adapterTablas.setSelection(it)
        }
        viewModel.liveTrabajadoresIds.observe(viewLifecycleOwner) {
            adapterTrabajadores.setSelection(it)
        }
        viewModel.errorLD.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
        binding.selectTrabajadores.setOnClickListener {
            findNavController()
                .navigate(R.id.action_form_actividad_trabajador_to_lista_trabajadores_registros)
        }
        binding.recyclerActividades.setHasFixedSize(true)
        binding.recyclerActividades.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerActividades.adapter = adapterActividades
        binding.selectTablas.setOnClickListener {
            findNavController()
                .navigate(R.id.action_form_actividad_trabajador_to_lista_tablas_registros)
        }
        binding.recyclerTablas.setHasFixedSize(true)
        binding.recyclerTablas.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTablas.adapter = adapterTablas
        binding.selectActividades.setOnClickListener {
            findNavController()
                .navigate(R.id.action_form_actividad_trabajador_to_lista_actividades_registros)
        }
        binding.recyclerTrabajadores.setHasFixedSize(true)
        binding.recyclerTrabajadores.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTrabajadores.adapter = adapterTrabajadores

        binding.formActividadesRegistrar.setOnClickListener {

            arguments?.getInt("type", 0)?.let {
                when (it) {
                    0 -> if (viewModel.registrarActividad())
                        findNavController().popBackStack()
                    1 -> findNavController()
                        .navigate(R.id.action_form_actividad_trabajador_to_form_extra)
                    2 -> findNavController()
                        .navigate(R.id.action_form_actividad_trabajador_to_form_bono)
                }
            }
            Log.i("TAG", "onViewCreated: ${arguments?.getInt("type")}")
        }
    }
}