package mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.BonoWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemTrabajadorBonoBinding

class BonoViewHolder(
    private val binding: ItemTrabajadorBonoBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(bono: BonoWithTrabajador) {
        binding.bono = bono
    }
}