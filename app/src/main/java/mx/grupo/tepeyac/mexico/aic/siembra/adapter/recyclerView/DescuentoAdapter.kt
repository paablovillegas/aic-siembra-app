package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.descuento.DescuentoWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class DescuentoAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var descuentos: List<DescuentoWithTrabajador> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val descuento = descuentos[position]
        holder.bind(
            descuento.trabajador.getNombreCompleto(),
            String.format("%d asistencias", descuento.descuento.fecha.time)
        )
    }

    override fun getItemCount(): Int = descuentos.size

    fun combineGrupos(lista: List<DescuentoWithTrabajador>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = descuentos.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                descuentos[i].descuento.id == lista[j].descuento.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                descuentos[i].trabajador.getNombreCompleto() ==
                        lista[j].trabajador.getNombreCompleto()
        })
        descuentos = lista
        diff.dispatchUpdatesTo(this)
    }
}