package model

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object TB_User_task : Table() {
    val id_task = long("id_task").primaryKey().autoIncrement()
    val task_title = varchar("task_title", 50)
    val task_description = text("task_description")
    val task_limit = datetime("task_limit").nullable()
    val task_done = datetime("task_done").nullable()
    val fk_user = long("fk_user").references(TB_User.id_user)
}

data class UserTask (
        val id_task : Long,
        val task_title : String,
        val task_description : String,
        val task_limit : DateTime?,
        val task_done : DateTime?,
        val fk_user : Long
)

data class NewUserTask(
        val id_task : Long?,
        val task_title : String,
        val task_description : String,
        val task_limit : DateTime?,
        val task_done : DateTime?,
        val fk_user : Long
)