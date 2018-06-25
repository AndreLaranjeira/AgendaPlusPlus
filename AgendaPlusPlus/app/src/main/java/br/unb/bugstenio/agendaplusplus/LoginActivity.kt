package br.unb.bugstenio.agendaplusplus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.unb.bugstenio.agendaplusplus.model.Object.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.nav_header_main_navigation_drawer.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_login_button!!.setOnClickListener {
            Session.user = User(
                    0,
                    "@cubo",
                    "victoragcosta@hotmail.com",
                    "nudes",
                    Date(1998,10,17)
            )
            this.finish()
        }
    }
}
