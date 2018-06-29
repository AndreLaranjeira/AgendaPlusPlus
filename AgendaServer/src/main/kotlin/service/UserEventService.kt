package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class UserEventService {

    suspend fun getAllUserEvents(): List<UserEvent> = dbQuery {
        TB_User_event.selectAll().map { toUserEvent(it) }
    }

    suspend fun getUserEvent(id: Long): UserEvent? = dbQuery {
        TB_User_event.select {
            (TB_User_event.id_event eq id)
        }.mapNotNull { toUserEvent(it) }
                .singleOrNull()
    }

    suspend fun updateUserEvent(updatedUserEvent: NewUserEvent): UserEvent {
        val id = updatedUserEvent.id_event
        return if (id == null) {
            addUserEvent(updatedUserEvent)
        } else {
            dbQuery {
                TB_User_event.update({ TB_User_event.id_event eq id }) {
                    it[event_title] = updatedUserEvent.event_title
                    it[event_description] = updatedUserEvent.event_description
                    it[event_date] = updatedUserEvent.event_date
                    it[event_notification] = updatedUserEvent.event_notification
                    it[fk_user] = updatedUserEvent.fk_user
                }
            }
            getUserEvent(id)!!
        }
    }

    suspend fun addUserEvent(newUserEvent: NewUserEvent): UserEvent {
        var key: Long? = 0
        dbQuery {
            key = TB_User_event.insert({
                it[event_title] = newUserEvent.event_title
                it[event_description] = newUserEvent.event_description
                it[event_date] = newUserEvent.event_date
                it[event_notification] = newUserEvent.event_notification
                it[fk_user] = newUserEvent.fk_user
            }) get TB_User_event.id_event
        }
        return getUserEvent(key!!)!!
    }

    suspend fun deleteUserEvent(id: Long): Boolean = dbQuery {
        TB_User_event.deleteWhere { TB_User_event.id_event eq id } > 0
    }

    private fun toUserEvent(row: ResultRow): UserEvent =
            UserEvent(
                    id_event = row[TB_User_event.id_event],
                    event_title = row[TB_User_event.event_title],
                    event_description = row[TB_User_event.event_description],
                    event_date = row[TB_User_event.event_date],
                    event_notification = row[TB_User_event.event_notification],
                    fk_user = row[TB_User_event.fk_user]
            )
}