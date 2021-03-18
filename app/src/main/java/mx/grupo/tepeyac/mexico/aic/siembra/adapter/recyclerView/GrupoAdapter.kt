package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.GrupoWithTrabajadores
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class GrupoAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var grupos: List<GrupoWithTrabajadores> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val grupo = grupos[position]
        holder.bind(grupo.grupo.grupo, String.format("Trabajadores: %d", grupo.trabajadores.size))
        holder.bindGrupo(grupo.grupo.id)
    }

    override fun getItemCount(): Int = grupos.size

    fun combineGrupos(newGrupos: List<GrupoWithTrabajadores>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = grupos.size

            override fun getNewListSize(): Int = newGrupos.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                grupos[i].grupo.id == newGrupos[j].grupo.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                grupos[i].grupo.grupo == newGrupos[j].grupo.grupo &&
                        grupos[i].trabajadores.size == newGrupos[j].trabajadores.size
        })
        grupos = newGrupos
        diff.dispatchUpdatesTo(this)
    }
}