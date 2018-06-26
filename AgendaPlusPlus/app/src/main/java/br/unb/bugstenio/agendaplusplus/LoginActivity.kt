package br.unb.bugstenio.agendaplusplus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.unb.bugstenio.agendaplusplus.model.DAO.UserDAO
import br.unb.bugstenio.agendaplusplus.model.Object.User
import br.unb.bugstenio.agendaplusplus.model.Object.parseUsers
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.nav_header_main_navigation_drawer.*
import org.joda.time.DateTime
import java.util.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_login_button!!.setOnClickListener {
            login_progress_bar.visibility = View.VISIBLE
            UserDAO().getAllUsers {
                login_progress_bar.visibility = View.GONE
                val users = it?.parseUsers()
                val user = users?.filter {
                    it.username == login_edit_username.text.toString()
                }.orEmpty()
                if(user.isEmpty()){
                    login_error_text.text = "Username incorreto!"
                } else if(user[0].password != login_edit_password.text.toString()) {
                    login_error_text.text = "Senha incorreta"
                } else {
                    Session.user = user[0]
                    this.finish()
                }
            }
        }
    }
}
