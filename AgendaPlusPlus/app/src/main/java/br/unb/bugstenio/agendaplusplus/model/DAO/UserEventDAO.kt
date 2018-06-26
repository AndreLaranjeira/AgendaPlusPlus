package br.unb.bugstenio.agendaplusplus.model.DAO

import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*
import org.json.JSONObject

class UserEventDAO: NetworkHandler() {
    val classPath = "/user/event"

    fun createUserEvent(newEvent: Event){
        val path = "/"
        val params = JSONObject()

        params.put("id_event", newEvent.id)
        params.put("event_title", newEvent.title)
        params.put("event_description", newEvent.description)
        params.put("event_date", newEvent.eventDate)
        params.put("event_notification", newEvent.eventNotification)
        params.put("fk_user", newEvent.externalId)

        apiController.post(classPath+path, params){response ->
        }
    }

    fun updateUserEvent(event: Event){
        val path = "/"
        val params = JSONObject()

        params.put("id_event", event.id)
        params.put("event_title", event.title)
        params.put("event_description", event.description)
        params.put("event_date", event.eventDate)
        params.put("event_notification", event.eventNotification)
        params.put("fk_user", event.externalId)

        apiController.update(classPath+path, params){response ->
        }
    }

    fun deleteUserEvent(event: Event){
        val path = "/%d".format(event.id)
        apiController.delete(classPath+path){response ->
        }
    }

}