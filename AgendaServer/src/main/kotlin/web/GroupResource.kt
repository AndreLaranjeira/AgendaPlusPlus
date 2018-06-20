package web

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import model.NewGroup
import service.GroupService

fun Route.group(groupService: GroupService) {

    route("/group") {

        get("/") {
            call.respond(groupService.getAllGroups())
        }

        get("/{id}") {
            val group = groupService.getGroup(call.parameters["id"]?.toInt()!!)
            if (group == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(group)
        }

        post("/") {
            val group = call.receive<NewGroup>()
            call.respond(groupService.addGroup(group))
        }

        put("/") {
            val group = call.receive<NewGroup>()
            call.respond(groupService.updateGroup(group))
        }

        delete("/{id}") {
            val removed = groupService.deleteGroup(call.parameters["id"]?.toInt()!!)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

    }
}