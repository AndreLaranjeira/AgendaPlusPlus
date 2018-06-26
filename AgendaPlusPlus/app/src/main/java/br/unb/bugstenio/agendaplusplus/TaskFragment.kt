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
import android.widget.Toast
import br.unb.bugstenio.agendaplusplus.model.Object.Task
import kotlinx.android.synthetic.main.fragment_list_layout.*
import org.joda.time.DateTime

class TaskFragment : Fragment(){

    private lateinit var recyclerView : RecyclerView
    private lateinit var viewAdapter : RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    @TargetApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewManager = LinearLayoutManager(this.context)
        viewAdapter = TaskListAdapter(listOf(Task(title = "Manda eus", description = "hahaha",
                limitDate = DateTime(2018,5,22,0,0))))

        recyclerView = tasklist.apply {
            layoutManager = viewManager!!
            adapter = viewAdapter!!
        }
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))

        list_fab.setOnClickListener {
            Toast.makeText(it.context,"bololohahahaha",Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_layout, container, false)
    }

}