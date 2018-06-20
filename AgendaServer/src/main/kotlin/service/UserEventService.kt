package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class UserEventService {

    suspend fun getAllUserEvents(): List<UserEvent> = dbQuery {
        TB_User_event.selectAll().map { toUserEvent(it) }
    }

    suspend fun getUserEvent(id: Int): UserEvent? = dbQuery {
        TB_User_event.select {
            (TB_User_event.id_event eq id)
        }.mapNotNull { toUserEvent(it) }
                .singleOrNull()
    }

    suspend fun updateUserEvent(UserEvent: NewUserEvent): UserEvent {
        val id = UserEvent.id_event
        return if (id == null) {
            addUserEvent(UserEvent)
        } else {
            dbQuery {
                TB_User_event.update({ TB_User_event.id_event eq id }) {
                    it[event_title] = UserEvent.event_title
                    it[event_description] = UserEvent.event_description
                    it[event_date] = UserEvent.event_date
                    it[event_notification] = UserEvent.event_notification
                    it[fk_user] = UserEvent.fk_user
                }
            }
            getUserEvent(id)!!
        }
    }

    suspend fun addUserEvent(UserEvent: NewUserEvent): UserEvent {
        var key: Int? = 0
        dbQuery {
            key = TB_User_event.insert({
                it[event_title] = UserEvent.event_title
                it[event_description] = UserEvent.event_description
                it[event_date] = UserEvent.event_date
                it[event_notification] = UserEvent.event_notification
                it[fk_user] = UserEvent.fk_user
            }) get TB_User_event.id_event
        }
        return getUserEvent(key!!)!!
    }

    suspend fun deleteUserEvent(id: Int): Boolean = dbQuery {
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