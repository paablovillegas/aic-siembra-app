package mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class CatalogoViewHolder(private val binding: ItemListaSimpleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(title: String, subtitle: String? = null) {
        binding.title.text = title
        if (subtitle == null)
            binding.subtitle.visibility = View.GONE
        else
            binding.subtitle.text = subtitle
    }

    fun bindCatalogo(index: Int) {
        binding.root.setOnClickListener {
            when (index) {
                0 -> it.findNavController().navigate(R.id.action_catalogosFragment_to_listaRanchos)
                1 -> it.findNavController().navigate(R.id.action_catalogos_fragment_to_listaAreas)
                2 -> it.findNavController().navigate(R.id.action_catalogos_fragment_to_lista_grupos)
                3 -> it.findNavController()
                    .navigate(R.id.action_catalogos_fragment_to_listaProductos)
            }
        }
    }

    fun bindRancho(idRancho: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idRancho)
            }
            it.findNavController().navigate(R.id.action_lista_ranchos_to_listaTablas, bundle)
        }
        binding.root.setOnLongClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idRancho)
            }
            it.findNavController().navigate(R.id.action_lista_ranchos_to_formRancho, bundle)
            return@setOnLongClickListener true
        }
    }

    fun bindTabla(idTabla: Long, idRancho: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idTabla)
            }
            it.findNavController().navigate(R.id.action_lista_tablas_to_lista_ciclos, bundle)
        }
        binding.root.setOnLongClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idTabla)
                this.putLong("id_rancho", idRancho)
            }
            it.findNavController().navigate(R.id.action_lista_tablas_to_form_tabla, bundle)
            return@setOnLongClickListener true
        }
    }

    fun bindCiclo(idCiclo: Long, idTabla: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idCiclo)
                this.putLong("id_tabla", idTabla)
            }
            it.findNavController().navigate(R.id.action_lista_ciclos_to_from_ciclo, bundle)
        }
    }

    fun bindArea(idArea: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idArea)
            }
            it.findNavController().navigate(R.id.action_listaAreas_to_listaActividades, bundle)
        }
        binding.root.setOnLongClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idArea)
            }
            it.findNavController().navigate(R.id.action_lista_tablas_to_form_tabla, bundle)
            return@setOnLongClickListener true
        }
    }

    fun bindActividad(idActividad: Long, idArea: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idActividad)
                this.putLong("id_area", idArea)
            }
            it.findNavController().navigate(R.id.action_lista_tablas_to_form_tabla, bundle)
        }
    }

    fun bindGrupo(idGrupo: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idGrupo)
            }
            it.findNavController().navigate(R.id.action_lista_grupos_to_lista_trabajadores, bundle)
        }
        binding.root.setOnLongClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idGrupo)
            }
            it.findNavController().navigate(R.id.action_lista_grupos_to_form_grupo, bundle)
            return@setOnLongClickListener true
        }
    }

    fun bindProducto(idProducto: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idProducto)
            }
            it.findNavController().navigate(R.id.action_lista_productos_to_form_producto, bundle)
        }
    }

    fun bindTrabajador(idTrabajador: Long, idGrupo: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idTrabajador)
                this.putLong("id_grupo", idGrupo)
            }
            it.findNavController()
                .navigate(R.id.action_lista_trabajadores_to_form_trabajador, bundle)
        }
    }

    fun bindRachoRegistro(idRancho: Long, vm: RegistrosViewModel) {
        binding.root.setOnClickListener {
            vm.idRancho = idRancho
            it.findNavController()
                .navigate(R.id.action_lista_ranchos_registros_to_registros_fragment)
        }
    }

    fun bindRegistro(index: Int) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putInt("id", index)
            }
            it.findNavController()
                .navigate(R.id.action_registros_fragment_to_lista_grupos_registros, bundle)
        }
    }

    fun bindGrupoAsistencia(id_grupo: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", id_grupo)
            }
            it.findNavController()
                .navigate(R.id.action_lista_grupos_registros_to_lista_asistencias, bundle)
        }
    }

    fun bindGrupoActividades(id_grupo: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", id_grupo)
            }
            it.findNavController()
                .navigate(R.id.action_lista_grupos_registros_to_listaActividades, bundle)
        }
    }

    fun bindGrupoExtras(id_grupo: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", id_grupo)
            }
            it.findNavController()
                .navigate(R.id.action_lista_grupos_registros_to_listaExtras, bundle)
        }
    }

    fun bindGrupoBonos(id_grupo: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", id_grupo)
            }
            it.findNavController()
                .navigate(R.id.action_lista_grupos_registros_to_listaBonos, bundle)
        }
    }

    fun bindGrupoDescuentos(id_grupo: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", id_grupo)
            }
            it.findNavController()
                .navigate(R.id.action_lista_grupos_registros_to_listaDescuentos, bundle)
        }
    }
}