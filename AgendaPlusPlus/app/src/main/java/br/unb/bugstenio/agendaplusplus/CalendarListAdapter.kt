package br.unb.bugstenio.agendaplusplus

import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.Object.Event
import br.unb.bugstenio.agendaplusplus.model.Object.Task
import org.joda.time.DateTime

class CalendarListAdapter(taskListDataset : List<Listable>) : RecyclerViewListAdapter<Listable>(taskListDataset) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = taskListDataset[position]
        (listOf("task_title", "task_description", "task_date")
                zip listOf(item.title, item.description, item.date.toString2()))
                .map {
            holder.linearLayout.findViewWithTag<TextView>(it.first).text = it.second as String? ?: ""
        }
    }

    fun resetDataset() {
        taskListDataset = emptyList()
    }

    fun addTasksDataset(tasks: List<Task>) {
        taskListDataset += tasks.map { Listable(it.title,it.description,it.limitDate) }
        this.notifyDataSetChanged()
    }

    fun addEventsDataset(events: List<Event>) {
        taskListDataset += events.map { Listable(it.title,it.description,it.eventDate) }
        this.notifyDataSetChanged()
    }

    fun replaceDataset(tasks: List<Task>, events: List<Event>) {
        taskListDataset = (tasks.map { Listable(it.title,it.description,it.limitDate) } +
        events.map { Listable(it.title, it.description, it.eventDate) })
        this.notifyDataSetChanged()
    }

}

data class Listable(val title: String, val description: String, val date: DateTime)