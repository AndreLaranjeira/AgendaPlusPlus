package br.unb.bugstenio.agendaplusplus

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import br.unb.bugstenio.agendaplusplus.model.DAO.ProjectEventDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.UserEventDAO
import br.unb.bugstenio.agendaplusplus.model.Object.Event
import br.unb.bugstenio.agendaplusplus.model.Object.parseProjectEvent
import br.unb.bugstenio.agendaplusplus.model.Object.parseUserEvent
import kotlinx.android.synthetic.main.activity_event_show.*
import org.joda.time.DateTime

class EventShowActivity : Activity() {

    var eventId : Long = 0
    var event: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_show)

        this.intent.let {
            eventId = it.getLongExtra(EventShowActivity.ARG1, 0)
        }

        if(Session.project == null){
            UserEventDAO().getUserEvent(eventId) {
                event = it?.parseUserEvent()!!

                event_show_title.text = event?.title ?: ""
                event_show_description.text = event?.description ?: ""
                event_show_event_date.text = "Data Limite: " + (event?.eventDate ?: "Não há data limite")
                event_show_event_notification.text = "Concluído: " + (event?.eventNotification ?: "Não foi feito")
                event_show_project.text = "Projeto: Não pertence a um projeto"
            }
        } else {
            ProjectEventDAO().getProjectEvent(eventId) {
                event = it?.parseProjectEvent()!!

                event_show_title.text = event?.title ?: ""
                event_show_description.text = event?.description ?: ""
                event_show_event_date.text = "Data Limite: " + (event?.eventDate?.toString2() ?: "Não há data limite")
                event_show_event_notification.text = "Concluído: " + (event?.eventNotification?.toString2() ?: "Não foi feito")
                event_show_project.text = "Projeto: " + (Session.project?.title ?: "Não pertence a um projeto")
            }
        }

        event_show_update_button.setOnClickListener {
            EventCreateEditActivity.editEvent(it.context, eventId, Session.project != null, Session.project)
        }

        event_show_delete_button.setOnClickListener {
            if(Session.project == null){
                UserEventDAO().deleteUserEvent(Event(eventId, "", "", DateTime()))
            } else {
                ProjectEventDAO().deleteProjectEvent(Event(eventId, "", "", DateTime()))
            }
            this.finish()
        }
    }

    companion object {

        val ARG1 = "event"

        fun showEvent(context: Context, event: Event) {
            val intent = Intent(context, EventShowActivity::class.java).apply {
                putExtra(ARG1, event.id)
            }
            context.startActivity(intent)
        }

    }
}
