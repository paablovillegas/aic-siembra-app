package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupoWithActividades
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class GrupoActividadesAdapter(
    private val viewModel: RegistrosViewModel
) : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var gruposWithActividades: List<AsistenciaGrupoWithActividades> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val grupo = gruposWithActividades[position]
        holder.bind(
            grupo.grupo.grupo,
            String.format("%d actividades", grupo.actividades.size)
        )
        holder.bindGrupoActividades(grupo.asistenciaGrupo.id, viewModel)
    }

    override fun getItemCount(): Int = gruposWithActividades.size

    fun combineGrupos(lista: List<AsistenciaGrupoWithActividades>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = gruposWithActividades.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                gruposWithActividades[i].grupo.id == lista[j].grupo.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                gruposWithActividades[i].grupo.grupo == lista[j].grupo.grupo &&
                        gruposWithActividades[i].actividades.size == lista[j].actividades.size
        })
        gruposWithActividades = lista
        diff.dispatchUpdatesTo(this)
    }
}