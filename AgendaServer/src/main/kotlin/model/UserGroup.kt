package model

import org.jetbrains.exposed.sql.Table

object TB_User_Group : Table() {
    val fk_group = long("fk_group").primaryKey().references(TB_Group.id_group)
    val fk_user = long("fk_user").primaryKey().references(TB_User.id_user)
    val is_admin = bool("is_admin")
}

data class UserGroup(
        val fk_group : Long,
        val fk_user : Long,
        val is_admin : Boolean
)

data class NewUserGroup(
        val fk_group : Long,
        val fk_user : Long,
        val is_admin : Boolean
)