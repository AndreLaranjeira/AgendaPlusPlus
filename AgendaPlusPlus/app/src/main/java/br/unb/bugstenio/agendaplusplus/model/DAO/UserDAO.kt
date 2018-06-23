package br.unb.bugstenio.agendaplusplus.model.DAO

import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*
import org.json.*


class UserDAO : NetworkHandler(){
    val classPath = "/user"

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
}