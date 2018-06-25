package br.unb.bugstenio.agendaplusplus

import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.Object.Task

class TaskListAdapter(taskListDataset : List<Task>) : RecyclerViewListAdapter<Task>(taskListDataset) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.linearLayout.findViewWithTag<TextView>("task_title").text =
                taskListDataset[position].title
        holder.linearLayout.findViewWithTag<TextView>("task_date").text =
                taskListDataset[position].limitDate?.toString() ?: ""
        holder.linearLayout.findViewWithTag<TextView>("task_description").text =
                taskListDataset[position].description

        holder.linearLayout.setOnClickListener {
            TaskShowActivity.showTask(it.context, taskListDataset[position])
        }
    }
}