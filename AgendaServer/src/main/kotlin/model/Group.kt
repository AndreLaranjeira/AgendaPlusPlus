package model

import org.jetbrains.exposed.sql.Table

object TB_Group : Table() {
    val id_group = long("id_group").primaryKey().autoIncrement()
    val group_title = varchar("group_title", 25)
    val group_description = varchar("group_description", 140).nullable()
}

data class Group(
        val id_group : Long,
        val group_title : String,
        val group_description : String?
)

data class NewGroup(
        val id_group : Long?,
        val group_title : String,
        val group_description : String?
)