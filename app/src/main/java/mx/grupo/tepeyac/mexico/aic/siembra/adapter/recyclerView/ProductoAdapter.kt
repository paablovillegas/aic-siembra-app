package mx.grupo.tepeyac.mexico.aic.siembra.adapter.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.CatalogoViewHolder
import mx.grupo.tepeyac.mexico.aic.siembra.data.producto.Producto
import mx.grupo.tepeyac.mexico.aic.siembra.databinding.ItemListaSimpleBinding

class ProductoAdapter : RecyclerView.Adapter<CatalogoViewHolder>() {
    private var productos: List<Producto> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogoViewHolder =
        CatalogoViewHolder(
            ItemListaSimpleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )

    override fun onBindViewHolder(holder: CatalogoViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto.producto)
        holder.bindProducto(producto.id)
    }

    override fun getItemCount(): Int = productos.size

    fun combineProductos(newProductos: List<Producto>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = productos.size

            override fun getNewListSize(): Int = newProductos.size

            override fun areItemsTheSame(i: Int, j: Int): Boolean =
                productos[i].id == newProductos[j].id

            override fun areContentsTheSame(i: Int, j: Int): Boolean =
                productos[i].producto == newProductos[j].producto
        })
        productos = newProductos
        diff.dispatchUpdatesTo(this)
    }
}