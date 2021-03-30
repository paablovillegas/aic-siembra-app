package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.BonoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.asistenciaGrupo.bono.BonoWithTrabajador
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemTrabajadorBonoBinding
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemTrabajadorExtraBinding

class TrabajadorBonoAdapter : RecyclerView.Adapter<BonoViewHolder>() {
    private var bonosWithTrabajador: List<BonoWithTrabajador> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BonoViewHolder =
        BonoViewHolder(
            ItemTrabajadorBonoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BonoViewHolder, position: Int) {
        holder.bind(bonosWithTrabajador[position])
    }

    override fun getItemCount(): Int = bonosWithTrabajador.size

    fun combineListas(lista: List<BonoWithTrabajador>) {
        val aux = lista.map { i ->
            return@map bonosWithTrabajador.find { it.trabajador.id == i.trabajador.id } ?: i
        }
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = bonosWithTrabajador.size

            override fun getNewListSize(): Int = aux.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                bonosWithTrabajador[i].trabajador.id == aux[j].trabajador.id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                bonosWithTrabajador[i].trabajador.getNombreCompleto() ==
                        aux[j].trabajador.getNombreCompleto() &&
                        bonosWithTrabajador[i].bono.total == aux[j].bono.total
        })
        bonosWithTrabajador = aux
        diff.dispatchUpdatesTo(this)
    }
}