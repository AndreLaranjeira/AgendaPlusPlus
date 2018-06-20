package model

import org.jetbrains.exposed.sql.Table

object TB_Project : Table() {
    val id_project = integer("id_project").primaryKey().autoIncrement()
    val project_title = varchar("project_title", 25)
    val project_description = varchar("project_description", 140).nullable()
    val is_active = bool("is_active")
    val fk_group = integer("fk_group").references(TB_Group.id_group)
    val fk_admin = integer("fk_admin").references(TB_User.id_user)
}

data class Project(
        val id_project : Int,
        val project_title : String,
        val project_description : String?,
        val is_active : Boolean,
        val fk_group : Int,
        val fk_admin : Int
)

data class NewProject(
        val id_project : Int?,
        val project_title : String,
        val project_description : String?,
        val is_active: Boolean,
        val fk_group : Int,
        val fk_admin : Int
)