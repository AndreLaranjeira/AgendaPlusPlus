package service

import model.*
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime
import service.DatabaseFactory.dbQuery

class UserTaskService {

    suspend fun getAllUserTasks(): List<UserTask> = dbQuery {
        UserTasks.selectAll().map { toUserTask(it) }
    }

    suspend fun getUserTask(id: Int): UserTask? = dbQuery {
        UserTasks.select {
            (UserTasks.id_task eq id)
        }.mapNotNull { toUserTask(it) }
                .singleOrNull()
    }

    suspend fun updateUserTask(UserTask: NewUserTask): UserTask {
        val id = UserTask.id_task
        return if (id == null) {
            addUserTask(UserTask)
        } else {
            dbQuery {
                UserTasks.update({ UserTasks.id_task eq id }) {
                    it[task_title] = UserTask.task_title
                    it[task_description] = UserTask.task_description
                    it[task_limit] = UserTask.task_limit
                }
            }
            getUserTask(id)!!
        }
    }

    suspend fun addUserTask(UserTask: NewUserTask): UserTask {
        var key: Int? = 0
        dbQuery {
            key = UserTasks.insert({
                it[task_title] = UserTask.task_title
                it[task_description] = UserTask.task_description
                it[task_limit] = UserTask.task_limit
            }) get UserTasks.id_task
        }
        return getUserTask(key!!)!!
    }

    suspend fun deleteUserTask(id: Int): Boolean = dbQuery {
        UserTasks.deleteWhere { UserTasks.id_task eq id } > 0
    }

    private fun toUserTask(row: ResultRow): UserTask =
            UserTask(
                    id_task = row[UserTasks.id_task],
                    task_title = row[UserTasks.task_title],
                    task_description = row[UserTasks.task_description],
                    task_limit = row[UserTasks.task_limit],
                    task_done = row[UserTasks.task_done]
            )
}
