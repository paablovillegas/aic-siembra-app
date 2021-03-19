package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.tabla.Tabla
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class TablaAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    var tablas: List<Tabla> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val tabla = tablas[position]
        holder.bind(tabla.tabla)
        holder.bindTabla(tabla.id, tabla.idRancho)
    }

    override fun getItemCount(): Int = tablas.size

    fun combineTablas(newTablas: List<Tabla>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = tablas.size

            override fun getNewListSize(): Int = newTablas.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                tablas[i].idTabla == newTablas[j].idTabla

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                tablas[i].tabla == newTablas[j].tabla
        })
        tablas = newTablas
        diff.dispatchUpdatesTo(this)
    }
}