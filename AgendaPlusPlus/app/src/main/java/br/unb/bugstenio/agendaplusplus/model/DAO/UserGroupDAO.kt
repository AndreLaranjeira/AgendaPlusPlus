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
}