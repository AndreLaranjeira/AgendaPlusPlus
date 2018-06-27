package br.unb.bugstenio.agendaplusplus


import android.app.Fragment
import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.DAO.UserDAO
import br.unb.bugstenio.agendaplusplus.model.Object.User
import br.unb.bugstenio.agendaplusplus.model.Object.parseUser
import kotlinx.android.synthetic.main.fragment_user_edit.*
import org.joda.time.DateTime
import org.joda.time.DateTimeZone


class UserEditFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_edit, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        user_edit_username.setText(Session.user!!.username, TextView.BufferType.EDITABLE)
        user_edit_email.setText(Session.user!!.email, TextView.BufferType.EDITABLE)
        user_edit_password.setText(Session.user!!.password, TextView.BufferType.EDITABLE)

        val now = DateTime.now(DateTimeZone.getDefault())

        user_edit_birth_date.updateDate(now.year, now.monthOfYear, now.dayOfMonth)

        user_edit_confirm_button.setOnClickListener {
            UserDAO().updateUser(User(
                    Session.user!!.id,
                    user_edit_username.text.toString(),
                    user_edit_email.text.toString(),
                    user_edit_password.text.toString(),
                    DateTime(user_edit_birth_date.year,
                            user_edit_birth_date.month,
                            user_edit_birth_date.dayOfMonth,
                            0,
                            0
                    )
            ))
            UserDAO().getUser(Session.user!!.id){
                Session.user = it!!.parseUser()
            }
        }

        user_edit_logout_button.setOnClickListener {
            Session.user = null
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}
