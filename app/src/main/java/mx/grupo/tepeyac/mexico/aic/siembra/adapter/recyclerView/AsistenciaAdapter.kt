package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.asistencia.AsistenciaWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class AsistenciaAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var asistencias: List<AsistenciaWithTrabajador> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val asistencia = asistencias[position]
        holder.bind(
            asistencia.trabajador.getNombreCompleto(),
            String.format("%d asistencias", asistencia.asistencia.entrada.time)
        )
        holder.bindGrupoAsistencia(asistencia.asistencia.idAsistenciaGrupo)
    }

    override fun getItemCount(): Int = asistencias.size

    fun combineGrupos(lista: List<AsistenciaWithTrabajador>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = asistencias.size

            override fun getNewListSize(): Int = lista.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                asistencias[i].asistencia.id == lista[j].asistencia.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                asistencias[i].trabajador.getNombreCompleto() ==
                        lista[j].trabajador.getNombreCompleto()
        })
        asistencias = lista
        diff.dispatchUpdatesTo(this)
    }
}