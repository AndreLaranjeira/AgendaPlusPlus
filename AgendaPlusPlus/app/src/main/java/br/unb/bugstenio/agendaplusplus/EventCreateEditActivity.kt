package br.unb.bugstenio.agendaplusplus

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import br.unb.bugstenio.agendaplusplus.model.DAO.ProjectEventDAO
import br.unb.bugstenio.agendaplusplus.model.DAO.UserEventDAO
import br.unb.bugstenio.agendaplusplus.model.Object.*
import kotlinx.android.synthetic.main.activity_event_create_edit.*
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

class EventCreateEditActivity : AppCompatActivity() {

    var eventId: Long? = null
    var event: Event? = null
    var projectId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_create_edit)

        this.intent.let {
            eventId = it.getLongExtra(ARG1, 0)
            val isFromProject = it.getBooleanExtra(ARG2, false)
            if(isFromProject){
                projectId = it.getLongExtra(ARG3, 0)
            }
        }

        if(eventId != 0.toLong()){
            if(projectId == null){
                UserEventDAO().getUserEvent(eventId!!) {
                    event = it?.parseUserEvent()
                    if(event != null){
                        event_create_title.setText(event!!.title, TextView.BufferType.EDITABLE)
                        event_create_description.setText(event!!.description, TextView.BufferType.EDITABLE)
                        val limit = event!!.eventDate
                        event_create_event_date.updateDate(limit.year, limit.monthOfYear, limit.dayOfMonth)
                        event_create_event_date_time.hour = limit.hourOfDay
                        event_create_event_date_time.minute = limit.minuteOfHour
                        val done = event!!.eventNotification
                        if (done != null) {
                            event_create_notification.toggle()
                            event_create_notification_date.updateDate(done.year, done.monthOfYear, done.dayOfMonth)
                            event_create_notification_time.hour = done.hourOfDay
                            event_create_notification_time.minute = done.minuteOfHour
                        }
                    }
                }
            } else {
                ProjectEventDAO().getProjectEvent(eventId!!) {
                    event = it?.parseProjectEvent()
                    if(event != null){
                        event_create_title.setText(event!!.title, TextView.BufferType.EDITABLE)
                        event_create_description.setText(event!!.description, TextView.BufferType.EDITABLE)
                        val limit = event!!.eventDate
                        event_create_event_date.updateDate(limit.year, limit.monthOfYear, limit.dayOfMonth)
                        event_create_event_date_time.hour = limit.hourOfDay
                        event_create_event_date_time.minute = limit.minuteOfHour
                        val done = event!!.eventNotification
                        if (done != null) {
                            event_create_notification.toggle()
                            event_create_notification_date.updateDate(done.year, done.monthOfYear, done.dayOfMonth)
                            event_create_notification_time.hour = done.hourOfDay
                            event_create_notification_time.minute = done.minuteOfHour
                        }
                    }
                }
            }
        }

        val now = DateTime.now(DateTimeZone.getDefault())
        event_create_event_date.updateDate(now.year, now.monthOfYear, now.dayOfMonth)
        event_create_event_date_time.hour = now.hourOfDay
        event_create_event_date_time.minute = now.minuteOfHour
        event_create_notification_date.updateDate(now.year, now.monthOfYear, now.dayOfMonth)
        event_create_notification_time.hour = now.hourOfDay
        event_create_notification_time.minute = now.minuteOfHour

        event_create_notification.setOnCheckedChangeListener { _, _ ->
            if(event_create_notification.isChecked){
                event_create_notification_layout.visibility = View.VISIBLE
            } else {
                event_create_notification_layout.visibility = View.GONE
            }
        }

        event_create_confirm_button.setOnClickListener {
            val title = event_create_title.text.toString()
            val description = event_create_description.text.toString()
            val eventDate = DateTime(
                    event_create_event_date.year,
                    event_create_event_date.month,
                    event_create_event_date.dayOfMonth,
                    event_create_event_date_time.hour,
                    event_create_event_date_time.minute
            )
            var notificationDate: DateTime? = null
            if(event_create_notification.isChecked){
                notificationDate = DateTime(
                        event_create_notification_date.year,
                        event_create_notification_date.month,
                        event_create_notification_date.dayOfMonth,
                        event_create_notification_time.hour,
                        event_create_notification_time.minute
                )
            }
            var externalId: Long? = projectId
            if(externalId == null){
                externalId = Session.user?.id
            }
            val newEvent = Event(
                    eventId!!,
                    title,
                    description,
                    eventDate,
                    notificationDate,
                    externalId
            )

            if(projectId == null){
                val dao = UserEventDAO()
                if(eventId == 0.toLong()) {
                    dao.createUserEvent(newEvent)
                } else {
                    dao.updateUserEvent(newEvent)
                }
            } else {
                val dao = ProjectEventDAO()
                if(eventId == 0.toLong()) {
                    dao.createProjectEvent(newEvent)
                } else {
                    dao.updateProjectEvent(newEvent)
                }
            }
            this.finish()
        }
    }

    companion object {

        val ARG1 = "event_id"
        val ARG2 = "is_from_project"
        val ARG3 = "project_id"

        fun createEvent(context: Context, isFromProject: Boolean, project: Project?) {
            val intent = Intent(context, EventCreateEditActivity::class.java).apply {
                putExtra(ARG1, 0)
                putExtra(ARG2, isFromProject)
                putExtra(ARG3, project?.id)
            }
            context.startActivity(intent)
        }

        fun editEvent(context: Context, eventId: Long, isFromProject: Boolean, project: Project?) {
            val intent = Intent(context, EventCreateEditActivity::class.java).apply {
                putExtra(ARG1, eventId)
                putExtra(ARG2, isFromProject)
                putExtra(ARG3, project?.id)
            }
            context.startActivity(intent)
        }
    }
}
