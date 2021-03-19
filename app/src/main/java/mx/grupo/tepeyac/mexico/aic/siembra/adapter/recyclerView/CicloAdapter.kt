package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.ciclo.Ciclo
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class CicloAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var ciclos: List<Ciclo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val ciclo = ciclos[position]
        holder.bind(ciclo.ciclo)
        holder.bindCiclo(ciclo.id, ciclo.idTabla)
    }

    override fun getItemCount(): Int = ciclos.size

    fun combineCiclos(newCiclos: List<Ciclo>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = ciclos.size

            override fun getNewListSize(): Int = newCiclos.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                ciclos[i].id == newCiclos[i].id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                ciclos[i].ciclo == newCiclos[j].ciclo
        })
        ciclos = newCiclos
        diff.dispatchUpdatesTo(this)
    }
}