package br.unb.bugstenio.agendaplusplus

import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.Object.Event

class EventListAdapter(eventListDataset: List<Event>) : RecyclerViewListAdapter<Event>(eventListDataset) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.linearLayout.findViewWithTag<TextView>("")
    }

}