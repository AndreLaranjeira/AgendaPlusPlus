package br.unb.bugstenio.agendaplusplus.model.DAO

import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*
import org.json.JSONObject

class ProjectDAO: NetworkHandler() {
    val classPath = "/project"

    fun createProject(newProject: Project){
        val path = "/"
        val params = JSONObject()

        params.put("id_project", newProject.id)
        params.put("project_title", newProject.title)
        params.put("project_description", newProject.description)
        params.put("is_active", newProject.isActive)
        params.put("fk_group", newProject.groupId)
        params.put("fk_admin", newProject.adminId)

        apiController.post(classPath+path, params){response ->
        }
    }
}