package br.unb.bugstenio.agendaplusplus.model.DAO

import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*
import org.json.JSONObject

class ProjectTaskDAO: NetworkHandler() {
    val classPath = "/project/task"

    fun createProjectTask(newTask: Task){
        val path = "/"
        val params = JSONObject()

        params.put("id_task", newTask.id)
        params.put("task_title", newTask.title)
        params.put("task_description", newTask.description)
        params.put("task_limit", newTask.limitDate)
        params.put("task_done", newTask.taskDone)
        params.put("fk_project", newTask.externalId)

        apiController.post(classPath+path, params){response ->
        }
    }
}