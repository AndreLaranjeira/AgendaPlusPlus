import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.joda.JodaModule
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException
import io.ktor.application.*
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.withCharset
import io.ktor.features.*
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.*
import service.*
import web.*

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
            registerModule(JodaModule())
        }
    }
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respond(TextContent("HTTP Status code ${it.value}: ${it.description}",
                                     ContentType.Text.Plain.withCharset(Charsets.UTF_8), it))
        }
        exception<MySQLIntegrityConstraintViolationException> {
            _ -> call.respond(HttpStatusCode.Conflict)
        }
        exception<Throwable> {
            _ -> call.respond(HttpStatusCode.InternalServerError)
        }
    }

    DatabaseFactory.init()

    install(Routing) {
        user(UserService())
        userTask(UserTaskService())
        userEvent(UserEventService())
        group(GroupService())
        userGroup(UserGroupService())
        project(ProjectService())
        projectTask(ProjectTaskService())
        projectEvent(ProjectEventService())
    }
}

/*
    Future task list:
    TODO Fix fields is_admin and is_active.

 */