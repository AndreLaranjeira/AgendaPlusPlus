package model

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object TB_Project_task : Table() {
    val id_task = long("id_task").primaryKey().autoIncrement()
    val task_title = varchar("task_title", 50)
    val task_description = text("task_description")
    val task_limit = datetime("task_limit").nullable()
    val task_done = datetime("task_done").nullable()
    val fk_project = long("fk_project").references(TB_Project.id_project)
}

data class ProjectTask (
        val id_task : Long,
        val task_title : String,
        val task_description : String,
        val task_limit : DateTime?,
        val task_done : DateTime?,
        val fk_project : Long
)

data class NewProjectTask(
        val id_task : Long?,
        val task_title : String,
        val task_description : String,
        val task_limit : DateTime?,
        val task_done : DateTime?,
        val fk_project : Long
)