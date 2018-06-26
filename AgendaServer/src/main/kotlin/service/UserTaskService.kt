package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class UserTaskService {

    suspend fun getAllUserTasks(): List<UserTask> = dbQuery {
        TB_User_task.selectAll().map { toUserTask(it) }
    }

    suspend fun getUserTask(id: Long): UserTask? = dbQuery {
        TB_User_task.select {
            (TB_User_task.id_task eq id)
        }.mapNotNull { toUserTask(it) }
                .singleOrNull()
    }

    suspend fun updateUserTask(updatedUserTask: NewUserTask): UserTask {
        val id = updatedUserTask.id_task
        return if (id == null) {
            addUserTask(updatedUserTask)
        } else {
            dbQuery {
                TB_User_task.update({ TB_User_task.id_task eq id }) {
                    it[task_title] = updatedUserTask.task_title
                    it[task_description] = updatedUserTask.task_description
                    it[task_limit] = updatedUserTask.task_limit
                    it[task_done] = updatedUserTask.task_done
                    it[fk_user] = updatedUserTask.fk_user
                }
            }
            getUserTask(id)!!
        }
    }

    suspend fun addUserTask(newUserTask: NewUserTask): UserTask {
        var key: Long? = 0
        dbQuery {
            key = TB_User_task.insert({
                it[task_title] = newUserTask.task_title
                it[task_description] = newUserTask.task_description
                it[task_limit] = newUserTask.task_limit
                it[task_done] = newUserTask.task_done
                it[fk_user] = newUserTask.fk_user
            }) get TB_User_task.id_task
        }
        return getUserTask(key!!)!!
    }

    suspend fun deleteUserTask(id: Long): Boolean = dbQuery {
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
