package br.unb.bugstenio.agendaplusplus.model.DAO

import android.util.Log
import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*

import org.joda.time.DateTime
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.*

data class UserCallback(var id_user: Long? =  null,
                        var username: String? = null,
                        var email: String? = null,
                        var password: String? = null,
                        var birth_date: DateTime? = null)

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

    fun getUser(id_user: Int) {
        val path = "/%d".format(id_user)
        apiController.get(classPath + path) { response ->
            val mapper = ObjectMapper()
            val parsedUser = mapper.readValue(response.toString(), UserCallback().javaClass)

        }
    }
}