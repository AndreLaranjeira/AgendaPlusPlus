package br.unb.bugstenio.agendaplusplus.model.DAO

import android.util.Log
import br.unb.bugstenio.agendaplusplus.R
import br.unb.bugstenio.agendaplusplus.model.Util.*
import com.android.volley.*
import com.android.volley.toolbox.*
import org.json.*

class UserDAO {
    val service = ServiceVolley()
    val apiController = APIController(service)


    fun createUser(){
        val path = "/user/add/"
        val params = JSONObject()

        params.put("username", "teste")
        params
    }
}