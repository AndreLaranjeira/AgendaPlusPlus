package br.unb.bugstenio.agendaplusplus.model.DAO

import android.util.Log
import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*

import org.joda.time.DateTime
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.*


class UserDAO : NetworkHandler(){
    private val classPath = "/user"

    fun createUser(newUser: User){
        val path = "/"
        val params = JSONObject()

        params.put("id_user", newUser.id)
        params.put("username", newUser.username)
        params.put("email", newUser.email)
        params.put("password", newUser.password)
        params.put("birth_date", newUser.birth_date)

        apiController.post(classPath+path, params){response ->
        }
    }

    fun getUser(id: Long, completionHandler: (JSONObject?) -> Unit) {
        val path = "/$id"

        apiController.get(classPath+path, completionHandler)
    }

    fun getAllUsers(completionHandler: (JSONArray?) -> Unit) {
        val path = "/all"

        apiController.getMany(classPath+path, completionHandler)
    }

    fun updateUser(user: User){
        val path = "/"
        val params = JSONObject()

        params.put("id_user", user.id)
        params.put("username", user.username)
        params.put("email", user.email)
        params.put("password", user.password)
        params.put("birth_date", user.birth_date)

        apiController.update(classPath+path, params){response ->
        }
    }

    fun deleteUser(user: User){
        val path = "/%d".format(user.id)
        apiController.delete(classPath+path){response ->
        }
    }
}