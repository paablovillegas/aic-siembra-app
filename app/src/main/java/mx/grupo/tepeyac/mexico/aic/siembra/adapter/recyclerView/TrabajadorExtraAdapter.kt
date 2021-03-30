package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.ExtraViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.extra.ExtraWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemTrabajadorExtraBinding

class TrabajadorExtraAdapter : RecyclerView.Adapter<ExtraViewHolder>() {
    private var extrasWitTrabajador: List<ExtraWithTrabajador> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExtraViewHolder =
        ExtraViewHolder(
            ItemTrabajadorExtraBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ExtraViewHolder, position: Int) {
        holder.bind(extrasWitTrabajador[position])
    }

    override fun getItemCount(): Int = extrasWitTrabajador.size

    fun combineListas(lista: List<ExtraWithTrabajador>) {
        val aux = lista.map { i ->
            return@map extrasWitTrabajador.find { it.trabajador.id == i.trabajador.id } ?: i
        }
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = extrasWitTrabajador.size

            override fun getNewListSize(): Int = aux.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                extrasWitTrabajador[i].trabajador.id == aux[j].trabajador.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                extrasWitTrabajador[i].trabajador.getNombreCompleto() ==
                        aux[j].trabajador.getNombreCompleto() &&
                        extrasWitTrabajador[i].extra.horas == aux[j].extra.horas &&
                        extrasWitTrabajador[i].extra.total == aux[j].extra.total
        })
        extrasWitTrabajador = aux
        diff.dispatchUpdatesTo(this)
    }
}