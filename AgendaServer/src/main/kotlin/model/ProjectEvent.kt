package model

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object TB_Project_event : Table() {
    val id_event = long("id_event").primaryKey().autoIncrement()
    val event_title = varchar("event_title", 50)
    val event_description = text("event_description")
    val event_date = datetime("event_date")
    val event_notification = datetime("event_notification").nullable()
    val fk_project = long("fk_project").references(TB_Project.id_project)
}

data class ProjectEvent (
        val id_event : Long,
        val event_title : String,
        val event_description : String,
        val event_date : DateTime,
        val event_notification : DateTime?,
        val fk_project : Long
)

data class NewProjectEvent(
        val id_event : Long?,
        val event_title : String,
        val event_description : String,
        val event_date : DateTime,
        val event_notification : DateTime?,
        val fk_project : Long
)