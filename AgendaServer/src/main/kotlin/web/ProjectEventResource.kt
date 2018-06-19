package web

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import model.NewProjectEvent
import service.ProjectEventService

fun Route.projectevent(projectEventService: ProjectEventService) {

    route("/projectevent") {

        get("/") {
            call.respond(projectEventService.getAllProjectEvents())
        }

        get("/{id}") {
            val projectEvent = projectEventService.getProjectEvent(call.parameters["id"]?.toInt()!!)
            if (projectEvent == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(projectEvent)
        }

        post("/") {
            val projectEvent = call.receive<NewProjectEvent>()
            call.respond(projectEventService.addProjectEvent(projectEvent))
        }

        put("/") {
            val projectEvent = call.receive<NewProjectEvent>()
            call.respond(projectEventService.updateProjectEvent(projectEvent))
        }

        delete("/{id}") {
            val removed = projectEventService.deleteProjectEvent(call.parameters["id"]?.toInt()!!)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

    }
}