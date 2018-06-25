package br.unb.bugstenio.agendaplusplus

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout


abstract class RecyclerViewListAdapter<T>(protected var taskListDataset: List<T>) :
        RecyclerView.Adapter<RecyclerViewListAdapter.ViewHolder>(){

    open val view: Int = R.layout.task_view

    class ViewHolder(val linearLayout: LinearLayout) : RecyclerView.ViewHolder(linearLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewListAdapter.ViewHolder {
        val linearLayout = LayoutInflater.from(parent.context)
                .inflate(view, parent, false) as LinearLayout

        return ViewHolder(linearLayout)
    }

    override fun getItemCount(): Int = taskListDataset.size

    fun replaceDataset(newDataset : List<T>) {
        taskListDataset = newDataset
        this.notifyDataSetChanged()
    }

}