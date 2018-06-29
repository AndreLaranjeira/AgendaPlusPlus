package br.unb.bugstenio.agendaplusplus

import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.Object.Event

class EventListAdapter(eventListDataset: List<Event>) : RecyclerViewListAdapter<Event>(eventListDataset) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.linearLayout.findViewWithTag<TextView>("task_title").text =
                taskListDataset[position].title
        holder.linearLayout.findViewWithTag<TextView>("task_date").text =
                taskListDataset[position].eventDate.toString2()
        holder.linearLayout.findViewWithTag<TextView>("task_description").text =
                taskListDataset[position].description

        holder.linearLayout.setOnClickListener {
            EventShowActivity.showEvent(it.context, taskListDataset[position])
        }
    }

}