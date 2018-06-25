package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class UserGroupService {

    suspend fun getAllUserGroups(): List<UserGroup> = dbQuery {
        TB_User_Group.selectAll().map { toUserGroup(it) }
    }

    suspend fun getUserGroup(id_group: Long, id_user: Long): UserGroup? = dbQuery {
        TB_User_Group.select {
            (TB_User_Group.fk_group eq id_group) and (TB_User_Group.fk_user eq id_user)
        }.mapNotNull { toUserGroup(it) }
                .singleOrNull()
    }

    suspend fun updateUserGroup(updatedUserGroup: NewUserGroup): UserGroup {
        val id_group = updatedUserGroup.fk_group
        val id_user = updatedUserGroup.fk_user

        return if(getUserGroup(id_group, id_user) == null)
            addUserGroup(updatedUserGroup)!!

        else {

            dbQuery {
                TB_User_Group.update({ (TB_User_Group.fk_group eq id_group) and (TB_User_Group.fk_user eq id_user) }) {
                    it[is_admin] = updatedUserGroup.is_admin
                }
            }

            getUserGroup(id_group, id_user)!!

        }

    }

    suspend fun addUserGroup(newUserGroup: NewUserGroup): UserGroup? {
        val group_key: Long = newUserGroup.fk_group
        val user_key: Long = newUserGroup.fk_user

        return if(getUserGroup(group_key, user_key) == null) {

            dbQuery {
                TB_User_Group.insert({
                    it[fk_group] = newUserGroup.fk_group
                    it[fk_user] = newUserGroup.fk_user
                    it[is_admin] = newUserGroup.is_admin
                })
            }

            getUserGroup(group_key, user_key)!!

        }

        else
            null

    }

    suspend fun deleteUserGroup(id_group: Long, id_user: Long): Boolean = dbQuery {
        TB_User_Group.deleteWhere { (TB_User_Group.fk_group eq id_group) and (TB_User_Group.fk_user eq id_user) } > 0
    }

    private fun toUserGroup(row: ResultRow): UserGroup =
            UserGroup(
                    fk_group = row[TB_User_Group.fk_group],
                    fk_user = row[TB_User_Group.fk_user],
                    is_admin = row[TB_User_Group.is_admin]
            )
}
