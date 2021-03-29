package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.SelectionViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSelectBinding

class SelectTrabajadorAdapter : RecyclerView.Adapter<SelectionViewHolder>() {
    private var trabajadores: List<Trabajador> = emptyList()
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
            val trabajador = trabajadores[position]
            holder.bind(it.isSelected(trabajador.id), trabajador.getNombreCompleto())
        }
    }

    override fun getItemCount(): Int = trabajadores.size

    override fun getItemId(i: Int): Long = trabajadores[i].id

    fun combineActividades(lista: List<Trabajador>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = trabajadores.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                trabajadores[i].id == lista[j].id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                trabajadores[i].getNombreCompleto() == lista[j].getNombreCompleto()

        })
        trabajadores = lista
        diff.dispatchUpdatesTo(this)
    }
}