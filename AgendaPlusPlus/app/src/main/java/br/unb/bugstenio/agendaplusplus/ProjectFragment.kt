package br.unb.bugstenio.agendaplusplus

import android.annotation.TargetApi
import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.unb.bugstenio.agendaplusplus.model.DAO.ProjectDAO
import br.unb.bugstenio.agendaplusplus.model.Object.*
import kotlinx.android.synthetic.main.fragment_list_layout.*

class ProjectFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    @TargetApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewManager = LinearLayoutManager(this.context)!!
        viewAdapter = ProjectListAdapter(emptyList())

        recyclerView = tasklist.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))

        ProjectDAO().getAllProjects {
            val projects = it?.parseProjects()
            //TODO: get groups and filter which projects are mine
            (viewAdapter as ProjectListAdapter).replaceDataset(
                projects!!
            )
        }

        list_fab.setOnClickListener {
            ProjectCreateEditActivity.createProject(it.context)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_layout, container, false)
    }

}