package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.SelectionViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSelectBinding

class SelectTablaAdapter : RecyclerView.Adapter<SelectionViewHolder>() {
    private var tablas: List<Tabla> = emptyList()
    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionViewHolder =
        SelectionViewHolder(
            ItemListaSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SelectionViewHolder, position: Int) {
        tracker?.let {
            val tabla = tablas[position]
            holder.bind(it.isSelected(tabla.id), tabla.tabla)
        }
    }

    override fun getItemCount(): Int = tablas.size

    override fun getItemId(i: Int): Long = tablas[i].id

    fun combineActividades(lista: List<Tabla>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = tablas.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                tablas[i].id == lista[j].id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                tablas[i].tabla == lista[j].tabla

        })
        tablas = lista
        diff.dispatchUpdatesTo(this)
    }
}