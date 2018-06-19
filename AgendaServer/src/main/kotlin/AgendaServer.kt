import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.features.*
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.jackson.jackson
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

    DatabaseFactory.init()

    install(Routing) {
        user(UserService())
        usertask(UserTaskService())
        userevent(UserEventService())
        group(GroupService())
        project(ProjectService())
        projecttask(ProjectTaskService())
    }
}

/*
    Task list:
    TODO Optimize imports and argument names.
    TODO Fix project field is_active.

 */