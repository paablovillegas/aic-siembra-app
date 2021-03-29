package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.SelectedViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSelectedBinding

class SelectedTrabajadorAdapter(
    private val source: List<Trabajador>
) : RecyclerView.Adapter<SelectedViewHolder>() {
    private var trabajadores: List<Trabajador> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder =
        SelectedViewHolder(
            ItemListaSelectedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
        holder.bind(trabajadores[position].getNombreCompleto())
    }

    override fun getItemCount(): Int = trabajadores.size

    fun setSelection(lista: List<Long>) {
        val aux: List<Trabajador> = source.filter { t ->
            lista.find { t.id == it } != null
        }
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = trabajadores.size

            override fun getNewListSize(): Int = aux.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                trabajadores[i].id == aux[j].id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                trabajadores[i].getNombreCompleto() == aux[j].getNombreCompleto()
        })
        trabajadores = aux
        diff.dispatchUpdatesTo(this)
    }
}