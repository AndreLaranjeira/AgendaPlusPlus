package br.unb.bugstenio.agendaplusplus.model.DAO

import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*
import org.json.*

class GroupDAO: NetworkHandler() {
    val classPath = "/group"

    fun createGroup(newGroup: Group){
        val path = "/"
        val params = JSONObject()

        params.put("id_group", newGroup.id)
        params.put("group_title", newGroup.title)
        params.put("group_description", newGroup.description)

        apiController.post(classPath+path, params){response ->

        }
    }

    fun getGroup(id: Long, completionHandler: (JSONObject?) -> Unit) {
        val path = "/$id"

        apiController.get(classPath+path, completionHandler)
    }

    fun getAllGroups(completionHandler: (JSONArray?) -> Unit) {
        val path = "/all"

        apiController.getMany(classPath+path, completionHandler)
    }

    fun updateGroup(group: Group){
        val path = "/"
        val params = JSONObject()

        params.put("id_group", group.id)
        params.put("group_title", group.title)
        params.put("group_description", group.description)

        apiController.update(classPath+path, params){response ->
        }
    }

    fun deleteGroup(group: Group){
        val path = "/%d".format(group.id)
        apiController.delete(classPath+path){response ->
        }
    }
}