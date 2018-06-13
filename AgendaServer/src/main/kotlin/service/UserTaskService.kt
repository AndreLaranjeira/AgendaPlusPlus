package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class UserTaskService {

    suspend fun getAllUserTasks(): List<UserTask> = dbQuery {
        User_Task.selectAll().map { toUserTask(it) }
    }

    suspend fun getUserTask(id: Int): UserTask? = dbQuery {
        User_Task.select {
            (User_Task.id_task eq id)
        }.mapNotNull { toUserTask(it) }
                .singleOrNull()
    }

    suspend fun updateUserTask(UserTask: NewUserTask): UserTask {
        val id = UserTask.id_task
        return if (id == null) {
            addUserTask(UserTask)
        } else {
            dbQuery {
                User_Task.update({ User_Task.id_task eq id }) {
                    it[task_title] = UserTask.task_title
                    it[task_description] = UserTask.task_description
                    it[task_limit] = UserTask.task_limit
                    it[task_done] = UserTask.task_done
                    it[fk_user] = UserTask.fk_user
                }
            }
            getUserTask(id)!!
        }
    }

    suspend fun addUserTask(UserTask: NewUserTask): UserTask {
        var key: Int? = 0
        dbQuery {
            key = User_Task.insert({
                it[task_title] = UserTask.task_title
                it[task_description] = UserTask.task_description
                it[task_limit] = UserTask.task_limit
                it[task_done] = UserTask.task_done
                it[fk_user] = UserTask.fk_user
            }) get User_Task.id_task
        }
        return getUserTask(key!!)!!
    }

    suspend fun deleteUserTask(id: Int): Boolean = dbQuery {
        User_Task.deleteWhere { User_Task.id_task eq id } > 0
    }

    private fun toUserTask(row: ResultRow): UserTask =
            UserTask(
                    id_task = row[User_Task.id_task],
                    task_title = row[User_Task.task_title],
                    task_description = row[User_Task.task_description],
                    task_limit = row[User_Task.task_limit],
                    task_done = row[User_Task.task_done],
                    fk_user = row[User_Task.fk_user]
            )
}
