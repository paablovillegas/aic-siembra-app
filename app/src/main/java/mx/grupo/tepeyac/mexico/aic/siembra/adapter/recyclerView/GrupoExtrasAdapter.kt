package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupoWithExtras
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class GrupoExtrasAdapter(
    private val viewModel: RegistrosViewModel
) : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var gruposWithExtras: List<AsistenciaGrupoWithExtras> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val grupo = gruposWithExtras[position]
        holder.bind(
            grupo.grupo.grupo,
            String.format("%d asistencias", grupo.extras.size)
        )
        holder.bindGrupoExtras(grupo.asistenciaGrupo.id, viewModel)
    }

    override fun getItemCount(): Int = gruposWithExtras.size

    fun combineGrupos(lista: List<AsistenciaGrupoWithExtras>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = gruposWithExtras.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                gruposWithExtras[i].grupo.id == lista[j].grupo.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                gruposWithExtras[i].grupo.grupo == lista[j].grupo.grupo &&
                        gruposWithExtras[i].extras.size == lista[j].extras.size
        })
        gruposWithExtras = lista
        diff.dispatchUpdatesTo(this)
    }
}