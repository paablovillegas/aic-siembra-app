package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.actividadTrabajador.ActividadWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class ActividadTrabajadorAdapter(
    private val viewModel: RegistrosViewModel
) : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var actividades: List<ActividadWithTrabajador> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val actividad = actividades[position]
        holder.bind(
            actividad.trabajador.getNombreCompleto(),
            String.format("%d actividades", actividad.actividadTrabajador.fecha.time)
        )
        holder.bindGrupoActividades(actividad.actividadTrabajador.idAsistenciaGrupo, viewModel)
    }

    override fun getItemCount(): Int = actividades.size

    fun combineGrupos(lista: List<ActividadWithTrabajador>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = actividades.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                actividades[i].actividadTrabajador.id == lista[j].actividadTrabajador.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                actividades[i].trabajador.getNombreCompleto() ==
                        lista[j].trabajador.getNombreCompleto()
        })
        actividades = lista
        diff.dispatchUpdatesTo(this)
    }
}