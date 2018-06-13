package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class UserEventService {

    suspend fun getAllUserEvents(): List<UserEvent> = dbQuery {
        User_Event.selectAll().map { toUserEvent(it) }
    }

    suspend fun getUserEvent(id: Int): UserEvent? = dbQuery {
        User_Event.select {
            (User_Event.id_event eq id)
        }.mapNotNull { toUserEvent(it) }
                .singleOrNull()
    }

    suspend fun updateUserEvent(UserEvent: NewUserEvent): UserEvent {
        val id = UserEvent.id_event
        return if (id == null) {
            addUserEvent(UserEvent)
        } else {
            dbQuery {
                User_Event.update({ User_Event.id_event eq id }) {
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
            key = User_Event.insert({
                it[event_title] = UserEvent.event_title
                it[event_description] = UserEvent.event_description
                it[event_date] = UserEvent.event_date
                it[event_notification] = UserEvent.event_notification
                it[fk_user] = UserEvent.fk_user
            }) get User_Event.id_event
        }
        return getUserEvent(key!!)!!
    }

    suspend fun deleteUserEvent(id: Int): Boolean = dbQuery {
        User_Event.deleteWhere { User_Event.id_event eq id } > 0
    }

    private fun toUserEvent(row: ResultRow): UserEvent =
            UserEvent(
                    id_event = row[User_Event.id_event],
                    event_title = row[User_Event.event_title],
                    event_description = row[User_Event.event_description],
                    event_date = row[User_Event.event_date],
                    event_notification = row[User_Event.event_notification],
                    fk_user = row[User_Event.fk_user]
            )
}