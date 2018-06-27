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
import br.unb.bugstenio.agendaplusplus.model.DAO.ProjectEventDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.ProjectTaskDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.UserEventDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.UserTaskDAO
import br.unb.bugstenio.agendaplusplus.model.Object.*
import kotlinx.android.synthetic.main.fragment_list_layout.*
import org.joda.time.DateTime
import java.util.*

class EventFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    @TargetApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewManager = LinearLayoutManager(this.context)!!
        viewAdapter = EventListAdapter(emptyList())

        recyclerView = tasklist.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))

        list_fab.setOnClickListener {
            EventCreateEditActivity.createEvent(it.context, false, null)
        }

        if(Session.project == null){
            UserEventDAO().getAllUserEvents {
                (viewAdapter as EventListAdapter).replaceDataset(
                        it?.parseUserEvents()?.filter {
                            it.externalId == Session.user?.id
                        }.orEmpty()
                )
            }
        } else {
            ProjectEventDAO().getAllProjectEvents {
                (viewAdapter as EventListAdapter).replaceDataset(
                        it?.parseProjectEvents()?.filter {
                            it.externalId == Session.project?.id
                        }.orEmpty()
                )
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_layout, container, false)
    }

}