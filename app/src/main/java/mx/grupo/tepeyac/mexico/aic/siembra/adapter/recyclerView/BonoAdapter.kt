package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.BonoWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class BonoAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var bonos: List<BonoWithTrabajador> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val bono = bonos[position]
        holder.bind(
            bono.trabajador.getNombreCompleto(),
            String.format("%d asistencias", bono.bono.fecha.time)
        )
        holder.bindGrupoBonos(bono.bono.idAsistenciaGrupo)
    }

    override fun getItemCount(): Int = bonos.size

    fun combineGrupos(lista: List<BonoWithTrabajador>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = bonos.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                bonos[i].bono.id == lista[j].bono.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                bonos[i].trabajador.getNombreCompleto() == lista[j].trabajador.getNombreCompleto()
        })
        bonos = lista
        diff.dispatchUpdatesTo(this)
    }
}