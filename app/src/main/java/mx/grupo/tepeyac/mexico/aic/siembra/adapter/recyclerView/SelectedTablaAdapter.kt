package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.SelectedViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSelectedBinding

class SelectedTablaAdapter(
    private val source: List<Tabla>
) : RecyclerView.Adapter<SelectedViewHolder>() {
    private var tablas: List<Tabla> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder =
        SelectedViewHolder(
            ItemListaSelectedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
        holder.bind(tablas[position].tabla)
    }

    override fun getItemCount(): Int = tablas.size

    fun setSelection(lista: List<Long>) {
        val aux: List<Tabla> = source.filter { t ->
            lista.find { t.id == it } != null
        }
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = tablas.size

            override fun getNewListSize(): Int = aux.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                tablas[i].id == aux[j].id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                tablas[i].tabla == aux[j].tabla
        })
        tablas = aux
        diff.dispatchUpdatesTo(this)
    }
}