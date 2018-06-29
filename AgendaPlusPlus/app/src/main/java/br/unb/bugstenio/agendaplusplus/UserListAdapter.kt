package br.unb.bugstenio.agendaplusplus

import android.view.View
import android.widget.Button
import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.DAO.UserDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.UserGroupDAO
import br.unb.bugstenio.agendaplusplus.model.Object.Group
import br.unb.bugstenio.agendaplusplus.model.Object.User

class UserListAdapter(listDataset: List<User>) : RecyclerViewListAdapter<User>(listDataset) {

    override val view: Int = R.layout.user_view

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.linearLayout.findViewWithTag<TextView>("user_username").text =
                taskListDataset[position].username
        holder.linearLayout.findViewWithTag<TextView>("user_email").text =
                taskListDataset[position].email
        holder.linearLayout.findViewWithTag<TextView>("user_birthdate").text =
                taskListDataset[position].birth_date.toString2()

        holder.linearLayout.findViewWithTag<Button>("user_remove_button").setOnClickListener {
            UserGroupDAO().deleteUserGroup(taskListDataset[position], Group(Session.project!!.groupId, "",""))
        }

        if(Session.project != null
                && Session.project!!.adminId == Session.user!!.id
                && taskListDataset[position].id != Session.user!!.id){
            holder.linearLayout.findViewWithTag<Button>("user_remove_button").visibility =
                    View.VISIBLE
        }
    }

    fun resetUsers(){
        taskListDataset = emptyList()
    }

    fun addUser(user: User){
        taskListDataset += user
        this.notifyDataSetChanged()
    }

}