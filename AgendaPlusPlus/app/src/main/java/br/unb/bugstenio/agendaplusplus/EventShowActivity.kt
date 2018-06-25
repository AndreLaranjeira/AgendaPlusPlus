package br.unb.bugstenio.agendaplusplus

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import br.unb.bugstenio.agendaplusplus.model.Object.Event
import br.unb.bugstenio.agendaplusplus.model.Object.Task
import kotlinx.android.synthetic.main.activity_event_show.*
import java.util.*

class EventShowActivity : Activity() {

    var eventId : Long = 0
    var event: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_show)

        this.intent.let {
            eventId = it.getLongExtra(EventShowActivity.ARG1, 0)
        }

        event = Event(eventId, "hahaha", "ajsdkljaklsd", Date(2018,5,24), Date(2018,5,26))

        event_show_title.text = event?.title ?: "Erro"
        event_show_description.text = event?.description ?: "Erro"
        event_show_event_date.text = "Data Realização: " + (event?.eventDate ?: "Erro")
        event_show_event_notification.text = "Notificar: " + (event?.eventNotification ?: "Não será notificado")
        event_show_project.text = "Projeto: " + (event?.projectId ?: "Não pertence a um projeto")

        event_show_update_button.setOnClickListener {
            Toast.makeText(it.context, "Update $eventId", Toast.LENGTH_LONG).show()
        }

        event_show_delete_button.setOnClickListener {
            Toast.makeText(it.context, "Delete $eventId", Toast.LENGTH_LONG).show()
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
