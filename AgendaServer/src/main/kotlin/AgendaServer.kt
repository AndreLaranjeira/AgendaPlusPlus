import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.features.*
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.withCharset
import io.ktor.jackson.jackson
import io.ktor.response.respond
import service.*
import web.*

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respond(TextContent("HTTP Status code ${it.value}: ${it.description}",
                                     ContentType.Text.Plain.withCharset(Charsets.UTF_8), it))
        }
        exception<Throwable> {
            _ -> call.respond(HttpStatusCode.InternalServerError)
        }
    }


    DatabaseFactory.init()

    install(Routing) {
        user(UserService())
        usertask(UserTaskService())
        userevent(UserEventService())
        group(GroupService())
        usergroup(UserGroupService())
        project(ProjectService())
        projecttask(ProjectTaskService())
        projectevent(ProjectEventService())
    }
}

/*
    Task list:
    TODO Optimize imports and argument names.
    TODO Fix project field is_active.
    TODO Change PK variable type in Table classes.
    TODO Test POST, PUT, DELETE requests.
    TODO Refactor route names.

 */