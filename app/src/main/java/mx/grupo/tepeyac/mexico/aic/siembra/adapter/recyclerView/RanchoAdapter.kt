package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoWithTablas
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class RanchoAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var ranchos: List<RanchoWithTablas> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val rancho = ranchos[position]
        holder.bind(rancho.rancho.rancho, String.format("Tablas: %d", rancho.tablas.size))
        holder.bindRancho(rancho.rancho.id)
    }

    override fun getItemCount(): Int = ranchos.size

    fun combineRanchos(newRanchos: List<RanchoWithTablas>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = ranchos.size

            override fun getNewListSize(): Int = newRanchos.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                ranchos[i].rancho.idRancho == newRanchos[j].rancho.idRancho

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                ranchos[i].rancho == newRanchos[j].rancho &&
                        ranchos[i].tablas.size == newRanchos[j].tablas.size
        })
        ranchos = newRanchos
        diff.dispatchUpdatesTo(this)
    }
}