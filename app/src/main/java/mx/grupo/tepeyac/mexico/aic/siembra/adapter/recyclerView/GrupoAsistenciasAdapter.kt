package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupoWithAsistencias
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class GrupoAsistenciasAdapter(
    private val viewModel: RegistrosViewModel
) : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var gruposWithAsistencia: List<AsistenciaGrupoWithAsistencias> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val grupo = gruposWithAsistencia[position]
        holder.bind(
            grupo.grupo.grupo,
            String.format("%d asistencia(s)", grupo.asistencias.size)
        )
        holder.bindGrupoAsistencia(grupo.asistenciaGrupo.id, viewModel)
    }

    override fun getItemCount(): Int = gruposWithAsistencia.size

    fun combineGrupos(lista: List<AsistenciaGrupoWithAsistencias>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = gruposWithAsistencia.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                gruposWithAsistencia[i].grupo.id == lista[j].grupo.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                gruposWithAsistencia[i].grupo.grupo == lista[j].grupo.grupo &&
                        gruposWithAsistencia[i].asistencias.size == lista[j].asistencias.size
        })
        gruposWithAsistencia = lista
        diff.dispatchUpdatesTo(this)
    }
}