package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.rancho.RanchoWithTablas
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding
import mx.grupo.tepeyac.mexico.aic.siembra.ui.registros.RegistrosViewModel

class RanchoRegistroAdapter(
    private val ranchos: List<RanchoWithTablas>,
    private val viewModel: RegistrosViewModel,
) :
    RecyclerView.Adapter<CatalogoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val rancho = ranchos[position]
        holder.bind(rancho.rancho.rancho)
        holder.bindRachoRegistro(rancho.rancho.id, viewModel)
    }

    override fun getItemCount(): Int = ranchos.size
}