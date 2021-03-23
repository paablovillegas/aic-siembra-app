package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.AsistenciaGrupoWithAsistencias
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class GrupoAsistenciasAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var grupoWithAsistencia: List<AsistenciaGrupoWithAsistencias> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val grupo = grupoWithAsistencia[position]
        holder.bind(
            grupo.grupo.grupo,
            String.format("%d asistencias", grupo.asistencias.size)
        )
    }

    override fun getItemCount(): Int = grupoWithAsistencia.size

    fun combineGrupos(lista: List<AsistenciaGrupoWithAsistencias>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = grupoWithAsistencia.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                grupoWithAsistencia[i].grupo.id == lista[j].grupo.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                grupoWithAsistencia[i].grupo.grupo == lista[j].grupo.grupo &&
                        grupoWithAsistencia[i].asistencias.size == lista[j].asistencias.size
        })
        grupoWithAsistencia = lista
        diff.dispatchUpdatesTo(this)
    }
}