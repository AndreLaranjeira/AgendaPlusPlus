package web

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import model.NewUser
import service.UserService

fun Route.user(userService: UserService) {

    route("/user") {

        get("/all") {
            call.respond(userService.getAllUsers())
        }

        get("/{id}") {
            val user = userService.getUser(call.parameters["id"]?.toLong()!!)
            if (user == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(user)
        }

        post("/") {
            val user = call.receive<NewUser>()
            call.respond(userService.addUser(user))
        }

        put("/") {
            val user = call.receive<NewUser>()
            call.respond(userService.updateUser(user))
        }

        delete("/{id}") {
            val removed = userService.deleteUser(call.parameters["id"]?.toLong()!!)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }

    }
}