package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupoWithDescuentos
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class GrupoDescuentosAdapter(
    private val viewModel: RegistrosViewModel
) : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var gruposWithDescuentos: List<AsistenciaGrupoWithDescuentos> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val grupo = gruposWithDescuentos[position]
        holder.bind(
            grupo.grupo.grupo,
            String.format("%d asistencias", grupo.descuentos.size)
        )
        holder.bindGrupoDescuentos(grupo.asistenciaGrupo.id, viewModel)
    }

    override fun getItemCount(): Int = gruposWithDescuentos.size

    fun combineGrupos(lista: List<AsistenciaGrupoWithDescuentos>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = gruposWithDescuentos.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                gruposWithDescuentos[i].grupo.id == lista[j].grupo.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                gruposWithDescuentos[i].grupo.grupo == lista[j].grupo.grupo &&
                        gruposWithDescuentos[i].descuentos.size == lista[j].descuentos.size
        })
        gruposWithDescuentos = lista
        diff.dispatchUpdatesTo(this)
    }
}