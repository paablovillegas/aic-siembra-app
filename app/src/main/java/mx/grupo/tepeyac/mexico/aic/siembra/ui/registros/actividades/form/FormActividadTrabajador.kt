package mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.actividades.form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import mx.grupo.tepeyac.mexico.aic.siembra.R
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
            viewModel.idRancho,
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


        viewModel.liveActividadesIds.observe(viewLifecycleOwner) {
            Log.i("TAG", "A onViewCreated: $it")
        }
        viewModel.liveTablasIds.observe(viewLifecycleOwner) {
            Log.i("TAG", "TA onViewCreated: $it")
        }
        viewModel.liveTrabajadoresIds.observe(viewLifecycleOwner) {
            Log.i("TAG", "TR onViewCreated: $it")
        }

        binding.selectTrabajadores.setOnClickListener {
            findNavController()
                .navigate(R.id.action_form_actividad_trabajador_to_lista_trabajadores_registros)
        }
        binding.selectTablas.setOnClickListener {
            findNavController()
                .navigate(R.id.action_form_actividad_trabajador_to_lista_tablas_registros)
        }
        binding.selectActividades.setOnClickListener {
            findNavController()
                .navigate(R.id.action_form_actividad_trabajador_to_lista_actividades_registros)
        }
    }
}