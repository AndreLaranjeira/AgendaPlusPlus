package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class UserTaskService {

    suspend fun getAllUserTasks(): List<UserTask> = dbQuery {
        TB_User_task.selectAll().map { toUserTask(it) }
    }

    suspend fun getUserTask(id: Int): UserTask? = dbQuery {
        TB_User_task.select {
            (TB_User_task.id_task eq id)
        }.mapNotNull { toUserTask(it) }
                .singleOrNull()
    }

    suspend fun updateUserTask(UserTask: NewUserTask): UserTask {
        val id = UserTask.id_task
        return if (id == null) {
            addUserTask(UserTask)
        } else {
            dbQuery {
                TB_User_task.update({ TB_User_task.id_task eq id }) {
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
            key = TB_User_task.insert({
                it[task_title] = UserTask.task_title
                it[task_description] = UserTask.task_description
                it[task_limit] = UserTask.task_limit
                it[task_done] = UserTask.task_done
                it[fk_user] = UserTask.fk_user
            }) get TB_User_task.id_task
        }
        return getUserTask(key!!)!!
    }

    suspend fun deleteUserTask(id: Int): Boolean = dbQuery {
        TB_User_task.deleteWhere { TB_User_task.id_task eq id } > 0
    }

    private fun toUserTask(row: ResultRow): UserTask =
            UserTask(
                    id_task = row[TB_User_task.id_task],
                    task_title = row[TB_User_task.task_title],
                    task_description = row[TB_User_task.task_description],
                    task_limit = row[TB_User_task.task_limit],
                    task_done = row[TB_User_task.task_done],
                    fk_user = row[TB_User_task.fk_user]
            )
}
