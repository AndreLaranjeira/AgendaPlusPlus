package web

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import model.NewProject
import service.ProjectService

fun Route.project(projectService: ProjectService) {

    route("/project") {

        get("/all") {
            call.respond(projectService.getAllProjects())
        }

        get("/{id}") {
            val project = projectService.getProject(call.parameters["id"]?.toInt()!!)
            if (project == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(project)
        }

        post("/") {
            val project = call.receive<NewProject>()
            call.respond(projectService.addProject(project))
        }

        put("/") {
            val project = call.receive<NewProject>()
            call.respond(projectService.updateProject(project))
        }

        delete("/{id}") {
            val removed = projectService.deleteProject(call.parameters["id"]?.toInt()!!)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

    }
}