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

    fun updateProject(project: Project){
        val path = "/"
        val params = JSONObject()

        params.put("id_project", project.id)
        params.put("project_title", project.title)
        params.put("project_description", project.description)
        params.put("is_active", project.isActive)
        params.put("fk_group", project.groupId)
        params.put("fk_admin", project.adminId)

        apiController.update(classPath+path, params){response ->
        }
    }

    fun deleteProject(project: Project){
        val path = "/%d".format(project.id)
        apiController.delete(classPath+path){response ->
        }
    }
}