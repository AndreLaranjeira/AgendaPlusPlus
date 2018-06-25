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


}