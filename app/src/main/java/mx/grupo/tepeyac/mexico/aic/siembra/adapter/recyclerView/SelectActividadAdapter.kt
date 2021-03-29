package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.SelectionViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.ActividadWithArea
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSelectBinding

class SelectActividadAdapter : RecyclerView.Adapter<SelectionViewHolder>() {
    private var actividades: List<ActividadWithArea> = emptyList()
    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionViewHolder =
        SelectionViewHolder(
            ItemListaSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SelectionViewHolder, position: Int) {
        tracker?.let {
            val actividad = actividades[position]
            holder.bind(
                it.isSelected(actividad.actividad.id),
                actividad.actividad.actividad,
                actividad.area.area
            )
        }
    }

    override fun getItemCount(): Int = actividades.size

    override fun getItemId(i: Int): Long = actividades[i].actividad.id

    fun combineActividades(lista: List<ActividadWithArea>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = actividades.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                actividades[i].actividad.id == lista[j].actividad.id &&
                        actividades[i].area.id == lista[j].area.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                actividades[i].actividad.actividad == lista[j].actividad.actividad &&
                        actividades[i].area.area == lista[j].area.area

        })
        actividades = lista
        diff.dispatchUpdatesTo(this)
    }
}