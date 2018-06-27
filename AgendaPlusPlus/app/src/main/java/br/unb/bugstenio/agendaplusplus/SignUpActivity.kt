package br.unb.bugstenio.agendaplusplus

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import br.unb.bugstenio.agendaplusplus.model.DAO.UserDAO
import br.unb.bugstenio.agendaplusplus.model.Object.User

import kotlinx.android.synthetic.main.activity_sign_up.*
import org.joda.time.DateTime

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sign_up_confirm_button.setOnClickListener {
            UserDAO().createUser(
                    User(
                            0,
                            sign_up_username.text.toString(),
                            sign_up_email.text.toString(),
                            sign_up_password.text.toString(),
                            DateTime(
                                    sign_up_birth_date.year,
                                    sign_up_birth_date.month,
                                    sign_up_birth_date.dayOfMonth,
                                    0,
                                    0
                            )
                    )
            )
            this.finish()
        }
    }

}
