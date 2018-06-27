package br.unb.bugstenio.agendaplusplus

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.unb.bugstenio.agendaplusplus.model.DAO.UserDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.UserGroupDAO
import br.unb.bugstenio.agendaplusplus.model.Object.Group
import br.unb.bugstenio.agendaplusplus.model.Object.parseUsers
import kotlinx.android.synthetic.main.activity_user_add.*
import java.security.AccessControlContext

class UserAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_add)

        user_add_confirm_button.setOnClickListener {
            UserDAO().getAllUsers {
                val users = it?.parseUsers()?.filter {
                    it.username == user_add_search.text.toString()
                }.orEmpty()
                if(users.isNotEmpty()){
                    UserGroupDAO().createUserGroup(users.first(), Group(Session.project!!.groupId, "", ""), false)
                }
            }
            this.finish()
        }
    }

    companion object {
        fun addUser(context: Context){
            val intent = Intent(context, UserAddActivity::class.java)
            context.startActivity(intent)
        }
    }
}
