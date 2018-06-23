package br.unb.bugstenio.agendaplusplus.model.DAO

import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*
import org.json.JSONObject

class ProjectEventDAO: NetworkHandler() {
    val classPath = "/project/event"

    fun createProjectEvent(newEvent: Event){
        val path = "/"
        val params = JSONObject()

        params.put("id_event", newEvent.id)
        params.put("event_title", newEvent.title)
        params.put("event_description", newEvent.description)
        params.put("event_date", newEvent.eventDate)
        params.put("event_notification", newEvent.eventNotification)
        params.put("fk_project", newEvent.externalId)

        apiController.post(classPath+path, params){response ->
        }
    }
}