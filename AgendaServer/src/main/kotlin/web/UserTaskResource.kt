package web

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import model.*
import service.*

fun Route.userTask(userTaskService: UserTaskService) {

    route("/user/task") {

        get("/all") {
            call.respond(userTaskService.getAllUserTasks())
        }

        get("/{id}") {
            val userTask = userTaskService.getUserTask(call.parameters["id"]?.toInt()!!)
            if (userTask == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(userTask)
        }

        post("/") {
            val userTask = call.receive<NewUserTask>()
            call.respond(userTaskService.addUserTask(userTask))
        }

        put("/") {
            val userTask = call.receive<NewUserTask>()
            call.respond(userTaskService.updateUserTask(userTask))
        }

        delete("/{id}") {
            val removed = userTaskService.deleteUserTask(call.parameters["id"]?.toInt()!!)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

    }
}