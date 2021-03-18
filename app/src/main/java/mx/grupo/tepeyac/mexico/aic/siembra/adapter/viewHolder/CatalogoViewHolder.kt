package mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.R
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

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
                3 -> it.findNavController().navigate(R.id.action_catalogos_fragment_to_listaProductos)
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
    }

    fun bindTabla(idTabla: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idTabla)
            }
            it.findNavController().navigate(R.id.action_lista_tablas_to_lista_ciclos, bundle)
        }
    }

    fun bindArea(idArea: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idArea)
            }
            it.findNavController().navigate(R.id.action_listaAreas_to_listaActividades, bundle)
        }
    }

    fun bindGrupo(idGrupo: Long) {
        binding.root.setOnClickListener {
            val bundle = Bundle().apply {
                this.putLong("id", idGrupo)
            }
            it.findNavController().navigate(R.id.action_lista_grupos_to_lista_trabajadores, bundle)
        }
    }

}