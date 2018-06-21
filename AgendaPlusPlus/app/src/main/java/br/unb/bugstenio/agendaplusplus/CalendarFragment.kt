package br.unb.bugstenio.agendaplusplus

import android.annotation.TargetApi
import android.os.Bundle
import android.app.Fragment
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*
import android.support.v7.widget.DividerItemDecoration
import br.unb.bugstenio.agendaplusplus.model.Object.Task
import br.unb.bugstenio.agendaplusplus.model.Service.TasksServicePlaceholder


class CalendarFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    @TargetApi(Build.VERSION_CODES.M)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //arguments?.let {
        //}
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar_view.setDate(calendar.timeInMillis, true, true)
        calendar_view.setOnDateChangeListener { _, year, month, day ->
            updateRecycler(
                    Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, day)
                        set(Calendar.HOUR_OF_DAY, 0)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                        set(Calendar.MILLISECOND, 0)
                    }.time
            )
        }

        viewManager = LinearLayoutManager(this.context)
        viewAdapter = TaskListAdapter(TasksServicePlaceholder().getTasksByDate(calendar.time))

        recyclerView = tasklist_of_the_day.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            //setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    fun updateRecycler(date: Date){
        (viewAdapter as TaskListAdapter).replaceDataset(TasksServicePlaceholder().getTasksByDate(date))
    }
}
