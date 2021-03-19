package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.actividad.Actividad
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class ActividadAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var actividades: List<Actividad> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val actividad = actividades[position]
        holder.bind(actividad.actividad)
        holder.bindActividad(actividad.id, actividad.idArea)
    }

    override fun getItemCount(): Int = actividades.size

    fun combineActividades(newActividades: List<Actividad>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = actividades.size

            override fun getNewListSize(): Int = newActividades.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                actividades[i].id == newActividades[j].id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                actividades[i].actividad == newActividades[j].actividad
        })
        actividades = newActividades
        diff.dispatchUpdatesTo(this)
    }
}