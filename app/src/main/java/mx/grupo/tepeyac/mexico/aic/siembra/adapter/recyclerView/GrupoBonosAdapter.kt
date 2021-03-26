package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupoWithBonos
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class GrupoBonosAdapter(
    private val viewModel: RegistrosViewModel
) : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var gruposWithBonos: List<AsistenciaGrupoWithBonos> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val grupo = gruposWithBonos[position]
        holder.bind(
            grupo.grupo.grupo,
            String.format("%d asistencias", grupo.bonos.size)
        )
        holder.bindGrupoBonos(grupo.asistenciaGrupo.id, viewModel)
    }

    override fun getItemCount(): Int = gruposWithBonos.size

    fun combineGrupos(lista: List<AsistenciaGrupoWithBonos>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = gruposWithBonos.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                gruposWithBonos[i].grupo.id == lista[j].grupo.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                gruposWithBonos[i].grupo.grupo == lista[j].grupo.grupo &&
                        gruposWithBonos[i].bonos.size == lista[j].bonos.size
        })
        gruposWithBonos = lista
        diff.dispatchUpdatesTo(this)
    }
}