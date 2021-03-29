package mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder

import android.view.View
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSelectBinding

class SelectionViewHolder(private val binding: ItemListaSelectBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(selected: Boolean, title: String, subtitle: String? = null) {
        binding.title.text = title
        binding.subtitle.text = subtitle
        if (subtitle == null)
            binding.subtitle.visibility = View.GONE
        else
            binding.subtitle.visibility = View.VISIBLE
        if (selected)
            binding.selection.visibility = View.VISIBLE
        else
            binding.selection.visibility = View.GONE
    }

    fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
        object : ItemDetailsLookup.ItemDetails<Long>() {
            override fun getPosition(): Int = adapterPosition
            override fun getSelectionKey(): Long = itemId
        }
}