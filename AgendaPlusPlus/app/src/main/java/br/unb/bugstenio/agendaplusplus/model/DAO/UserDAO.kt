package br.unb.bugstenio.agendaplusplus.model.DAO

import android.util.Log
import br.unb.bugstenio.agendaplusplus.R
import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*
import com.android.volley.*
import com.android.volley.toolbox.*
import com.google.gson.*
import org.json.*
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*

class UserDAO {
    val service = ServiceVolley()
    val apiController = APIController(service)


    fun createUser(newUser: User){
        val path = "/user/"
        val params = JSONObject()

        params.put("id_user", newUser.id)
        params.put("username", newUser.username)
        params.put("email", newUser.email)
        params.put("password", newUser.password)
        params.put("birth_date", newUser.birth_date)

        apiController.post(path, params){response ->
        }
    }
}