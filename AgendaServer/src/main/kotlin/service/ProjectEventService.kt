package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class ProjectEventService {

    suspend fun getAllProjectEvents(): List<ProjectEvent> = dbQuery {
        TB_Project_event.selectAll().map { toProjectEvent(it) }
    }

    suspend fun getProjectEvent(id: Int): ProjectEvent? = dbQuery {
        TB_Project_event.select {
            (TB_Project_event.id_event eq id)
        }.mapNotNull { toProjectEvent(it) }
                .singleOrNull()
    }

    suspend fun updateProjectEvent(updatedProjectEvent: NewProjectEvent): ProjectEvent {
        val id = updatedProjectEvent.id_event
        return if (id == null) {
            addProjectEvent(updatedProjectEvent)
        } else {
            dbQuery {
                TB_Project_event.update({ TB_Project_event.id_event eq id }) {
                    it[event_title] = updatedProjectEvent.event_title
                    it[event_description] = updatedProjectEvent.event_description
                    it[event_date] = updatedProjectEvent.event_date
                    it[event_notification] = updatedProjectEvent.event_notification
                    it[fk_project] = updatedProjectEvent.fk_project
                }
            }
            getProjectEvent(id)!!
        }
    }

    suspend fun addProjectEvent(newProjectEvent: NewProjectEvent): ProjectEvent {
        var key: Int? = 0
        dbQuery {
            key = TB_Project_event.insert({
                it[event_title] = newProjectEvent.event_title
                it[event_description] = newProjectEvent.event_description
                it[event_date] = newProjectEvent.event_date
                it[event_notification] = newProjectEvent.event_notification
                it[fk_project] = newProjectEvent.fk_project
            }) get TB_Project_event.id_event
        }
        return getProjectEvent(key!!)!!
    }

    suspend fun deleteProjectEvent(id: Int): Boolean = dbQuery {
        TB_Project_event.deleteWhere { TB_Project_event.id_event eq id } > 0
    }

    private fun toProjectEvent(row: ResultRow): ProjectEvent =
            ProjectEvent(
                    id_event = row[TB_Project_event.id_event],
                    event_title = row[TB_Project_event.event_title],
                    event_description = row[TB_Project_event.event_description],
                    event_date = row[TB_Project_event.event_date],
                    event_notification = row[TB_Project_event.event_notification],
                    fk_project = row[TB_Project_event.fk_project]
            )
}