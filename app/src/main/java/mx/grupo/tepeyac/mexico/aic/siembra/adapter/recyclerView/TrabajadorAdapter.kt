package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.grupo.trabajador.Trabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class TrabajadorAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var trabajadores: List<Trabajador> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val trabajador = trabajadores[position]
        holder.bind(trabajador.getNombreCompleto())
        holder.bindTrabajador(trabajador.id, trabajador.idGrupo)
    }

    override fun getItemCount(): Int = trabajadores.size

    fun combineTrabajadores(newTrabajadores: List<Trabajador>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = trabajadores.size

            override fun getNewListSize(): Int = newTrabajadores.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                trabajadores[i].id == newTrabajadores[j].id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                trabajadores[i].getNombreCompleto() == newTrabajadores[j].getNombreCompleto()
        })
        trabajadores = newTrabajadores
        diff.dispatchUpdatesTo(this)
    }
}