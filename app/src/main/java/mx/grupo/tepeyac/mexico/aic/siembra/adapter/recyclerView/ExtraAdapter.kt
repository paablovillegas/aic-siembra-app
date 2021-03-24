package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.ExtraWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class ExtraAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var extras: List<ExtraWithTrabajador> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val extra = extras[position]
        holder.bind(
            extra.trabajador.getNombreCompleto(),
            String.format("%d asistencias", extra.extra.fecha.time)
        )
        holder.bindGrupoExtras(extra.extra.idAsistenciaGrupo)
    }

    override fun getItemCount(): Int = extras.size

    fun combineGrupos(lista: List<ExtraWithTrabajador>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = extras.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                extras[i].extra.id == lista[j].extra.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                extras[i].trabajador.getNombreCompleto() == lista[j].trabajador.getNombreCompleto()
        })
        extras = lista
        diff.dispatchUpdatesTo(this)
    }
}