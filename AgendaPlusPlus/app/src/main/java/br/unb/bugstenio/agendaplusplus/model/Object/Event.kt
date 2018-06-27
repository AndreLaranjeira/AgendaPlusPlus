package br.unb.bugstenio.agendaplusplus.model.Object

import android.util.Log
import org.joda.time.*
import org.json.JSONArray
import org.json.JSONObject

// Classe para eventos a serem anotados na agenda.

data class Event(
        val id: Long = 0,
        val title: String,
        val description: String,
        val eventDate: DateTime,
        val eventNotification: DateTime? = null,
        val externalId: Long? = null
)

fun JSONObject.parseUserEvent(): Event {
    return Event(
            (this["id_event"] as Int).toLong(),
            this["event_title"] as String,
            this["event_description"] as String,
            DateTime(this["event_date"] as Long),
            DateTime(this["event_notification"] as Long),
            (this["fk_user"] as Int).toLong()
    )
}

fun JSONObject.parseProjectEvent(): Event {
    return Event(
            (this["id_event"] as Int).toLong(),
            this["event_title"] as String,
            this["event_description"] as String,
            DateTime(this["event_date"] as Long),
            DateTime(this["event_notification"] as Long),
            (this["fk_project"] as Int).toLong()
    )
}

fun JSONArray.parseUserEvents(): List<Event> {
    val events: MutableList<Event> = mutableListOf()
    for(i in 0 until this.length()){
        try {
            events.add((this[i] as JSONObject).parseUserEvent())
        } catch (e: Exception) {
            Log.e("Parse", "UserEvents JSONArray could not be parsed", e)
        }
    }
    return events
}

fun JSONArray.parseProjectEvents(): List<Event> {
    val events: MutableList<Event> = mutableListOf()
    for(i in 0 until this.length()){
        try {
            events.add((this[i] as JSONObject).parseProjectEvent())
        } catch (e: Exception) {
            Log.e("Parse", "ProjectEvents JSONArray could not be parsed", e)
        }
    }
    return events
}
