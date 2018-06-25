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
import br.unb.bugstenio.agendaplusplus.model.Object.Project
import br.unb.bugstenio.agendaplusplus.model.Object.User
import kotlinx.android.synthetic.main.fragment_list_layout.*
import java.util.*

class UserListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    @TargetApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewManager = LinearLayoutManager(this.context)!!
        viewAdapter = UserListAdapter(listOf(
                User(1, "@cubo", "cubo@doiiido.com", "catioro", Date(1998,10,17))
        ))

        recyclerView = tasklist.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
    }

    var projectId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let{
            projectId = it.getLong(ARG1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_layout, container, false)
    }

    companion object {

        val ARG1 = "project"

        fun newUserList(project: Project) =
                UserListFragment().apply {
                    arguments = Bundle().apply {
                        putLong(ARG1, project.id)
                    }
                }

    }

}