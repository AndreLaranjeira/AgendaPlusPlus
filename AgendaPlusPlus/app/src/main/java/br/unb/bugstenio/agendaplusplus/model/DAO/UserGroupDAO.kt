package br.unb.bugstenio.agendaplusplus.model.DAO

import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*
import org.json.JSONObject

class UserGroupDAO: NetworkHandler() {
    val classPath = "/user/group"

    fun createUserGroup(currentUser: User, currentGroup: Group, makeAdmin: Boolean){
        val path = "/"
        val params = JSONObject()

        params.put("fk_group", currentGroup.id)
        params.put("fk_user", currentUser.id)
        params.put("is_admin", makeAdmin)

        apiController.post(classPath+path, params){response ->
        }
    }

    fun updateUserGroup(currentUser: User, currentGroup: Group, makeAdmin: Boolean){
        val path = "/"
        val params = JSONObject()

        params.put("fk_group", currentGroup.id)
        params.put("fk_user", currentUser.id)
        params.put("is_admin", makeAdmin)

        apiController.update(classPath+path, params){response ->
        }
    }

    fun deleteUserGroup(user: User, group: Group){
        val path = "/%d/%d".format(group.id, user.id)
        apiController.delete(classPath+path){response ->
        }
    }
}