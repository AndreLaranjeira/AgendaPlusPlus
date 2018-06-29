package web

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import model.NewProjectTask
import service.ProjectTaskService

fun Route.projectTask(projectTaskService: ProjectTaskService) {

    route("/project/task") {

        get("/all") {
            call.respond(projectTaskService.getAllProjectTasks())
        }

        get("/{id}") {
            val projectTask = projectTaskService.getProjectTask(call.parameters["id"]?.toLong()!!)
            if (projectTask == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(projectTask)
        }

        post("/") {
            val projectTask = call.receive<NewProjectTask>()
            call.respond(projectTaskService.addProjectTask(projectTask))
        }

        put("/") {
            val projectTask = call.receive<NewProjectTask>()
            call.respond(projectTaskService.updateProjectTask(projectTask))
        }

        delete("/{id}") {
            val removed = projectTaskService.deleteProjectTask(call.parameters["id"]?.toLong()!!)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

    }
}