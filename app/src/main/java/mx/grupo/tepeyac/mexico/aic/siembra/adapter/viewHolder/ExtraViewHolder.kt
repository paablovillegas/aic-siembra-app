package mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.ExtraWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemTrabajadorExtraBinding

class ExtraViewHolder(
    private val binding: ItemTrabajadorExtraBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(extra: ExtraWithTrabajador) {
        binding.extra = extra
    }
}