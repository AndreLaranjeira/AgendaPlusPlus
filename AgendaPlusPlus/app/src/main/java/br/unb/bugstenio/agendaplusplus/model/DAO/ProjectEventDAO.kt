package br.unb.bugstenio.agendaplusplus.model.DAO

import br.unb.bugstenio.agendaplusplus.model.Object.*
import br.unb.bugstenio.agendaplusplus.model.Util.*
import org.json.JSONArray
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

    fun getProjectEvent(id: Long, completionHandler: (JSONObject?) -> Unit) {
        val path = "/$id"

        apiController.get(classPath+path, completionHandler)
    }

    fun getAllProjectEvents(completionHandler: (JSONArray?) -> Unit) {
        val path = "/all"

        apiController.getMany(classPath+path, completionHandler)
    }


    fun updateProjectEvent(event: Event){
        val path = "/"
        val params = JSONObject()

        params.put("id_event", event.id)
        params.put("event_title", event.title)
        params.put("event_description", event.description)
        params.put("event_date", event.eventDate)
        params.put("event_notification", event.eventNotification)
        params.put("fk_project", event.externalId)

        apiController.update(classPath+path, params){response ->
        }
    }

    fun deleteProjectEvent(event: Event){
        val path = "/%d".format(event.id)
        apiController.delete(classPath+path){response ->
        }
    }
}