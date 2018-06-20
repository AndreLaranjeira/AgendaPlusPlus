package model

import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime

object TB_Project_event : Table() {
    val id_event = integer("id_event").primaryKey().autoIncrement()
    val event_title = varchar("event_title", 50)
    val event_description = text("event_description")
    val event_date = datetime("event_date")
    val event_notification = datetime("event_notification").nullable()
    val fk_project = integer("fk_project").references(TB_Project.id_project)
}

data class ProjectEvent (
        val id_event : Int,
        val event_title : String,
        val event_description : String,
        val event_date : DateTime,
        val event_notification : DateTime?,
        val fk_project : Int
)

data class NewProjectEvent(
        val id_event : Int?,
        val event_title : String,
        val event_description : String,
        val event_date : DateTime,
        val event_notification : DateTime?,
        val fk_project : Int
)