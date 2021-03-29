package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.SelectedViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.area.ActividadWithArea
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSelectedBinding

class SelectedActividadAdapter(
    private val source: List<ActividadWithArea>
) : RecyclerView.Adapter<SelectedViewHolder>() {
    private var actividades: List<ActividadWithArea> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder =
        SelectedViewHolder(
            ItemListaSelectedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
        holder.bind(
            actividades[position].actividad.actividad
        )
    }

    override fun getItemCount(): Int = actividades.size

    fun setSelection(lista: List<Long>) {
        val aux: List<ActividadWithArea> = source.filter { t ->
            lista.find { t.actividad.id == it } != null
        }
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = actividades.size

            override fun getNewListSize(): Int = aux.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                actividades[i].actividad.id == aux[j].actividad.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                actividades[i].actividad.actividad == aux[j].actividad.actividad &&
                        actividades[i].area.area == aux[j].area.area
        })
        actividades = aux
        diff.dispatchUpdatesTo(this)
    }
}