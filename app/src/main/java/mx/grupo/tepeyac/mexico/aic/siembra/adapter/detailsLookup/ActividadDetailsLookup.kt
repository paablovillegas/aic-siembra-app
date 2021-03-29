package mx.grupo.tepeyac.mexico.aic.siembra.adapter.detailsLookup

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import mx.grupo.tepeyac.mexico.aic.siembra.adapter.viewHolder.SelectionViewHolder

class ActividadDetailsLookup(
    private val recyclerView: RecyclerView
) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as SelectionViewHolder)
                .getItemDetails()
        }
        return null
    }
}