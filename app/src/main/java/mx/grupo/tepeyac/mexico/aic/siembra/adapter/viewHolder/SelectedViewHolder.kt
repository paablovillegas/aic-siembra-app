package mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSelectedBinding

class SelectedViewHolder(private val binding: ItemListaSelectedBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(titulo: String) {
        binding.title.text = titulo
    }
}