package model

import org.jetbrains.exposed.sql.Table

object TB_User_Group : Table() {
    val fk_group = integer("fk_group").primaryKey().references(TB_Group.id_group)
    val fk_user = integer("fk_user").primaryKey().references(TB_User.id_user)
    val is_admin = bool("is_admin")
}

data class UserGroup(
        val fk_group : Int,
        val fk_user : Int,
        val is_admin : Boolean
)

data class NewUserGroup(
        val fk_group : Int,
        val fk_user : Int,
        val is_admin : Boolean
)