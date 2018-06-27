package br.unb.bugstenio.agendaplusplus

import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.Object.Project

class ProjectListAdapter(projectListDataset: List<Project>) : RecyclerViewListAdapter<Project>(projectListDataset) {

    override val view = R.layout.project_view

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.linearLayout.findViewWithTag<TextView>("project_title").text =
                taskListDataset[position].title
        holder.linearLayout.findViewWithTag<TextView>("project_description").text =
                taskListDataset[position].description

        holder.linearLayout.setOnClickListener {
            Session.project = taskListDataset[position]
            ProjectShowActivity.showProject(it.context, taskListDataset[position])
        }
    }

}