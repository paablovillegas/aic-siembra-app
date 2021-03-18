package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.AreaWithActividades
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class AreaAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var areas: List<AreaWithActividades> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val area = areas[position]
        holder.bind(area.area.area, String.format("Actividades: %d", area.actividades.size))
        holder.bindArea(area.area.id)
    }

    override fun getItemCount(): Int = areas.size

    fun combineAreas(newAreas: List<AreaWithActividades>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = areas.size

            override fun getNewListSize(): Int = newAreas.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                areas[i].area.id == newAreas[j].area.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                areas[i].area.area == newAreas[j].area.area &&
                        areas[i].actividades.size == newAreas[j].actividades.size
        })
        areas = newAreas
        diff.dispatchUpdatesTo(this)
    }
}