package br.unb.bugstenio.agendaplusplus

import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.Object.User

class UserListAdapter(listDataset: List<User>) : RecyclerViewListAdapter<User>(listDataset) {

    override val view: Int = R.layout.user_view

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.linearLayout.findViewWithTag<TextView>("user_username").text =
                taskListDataset[position].username
        holder.linearLayout.findViewWithTag<TextView>("user_email").text =
                taskListDataset[position].email
        holder.linearLayout.findViewWithTag<TextView>("user_birthdate").text =
                taskListDataset[position].birth_date.toString()
    }

}