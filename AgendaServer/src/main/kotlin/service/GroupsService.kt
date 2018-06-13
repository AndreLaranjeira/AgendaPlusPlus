package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class GroupService {

    suspend fun getAllGroups(): List<GroupDataClass> = dbQuery {
        Groups.selectAll().map { toGroup(it) }
    }

    suspend fun getGroup(id: Int): GroupDataClass? = dbQuery {
        Groups.select {
            (Groups.id_group eq id)
        }.mapNotNull { toGroup(it) }
                .singleOrNull()
    }

    suspend fun updateGroup(groupUpdated: NewGroup): GroupDataClass {
        val id = groupUpdated.id_group
        return if (id == null) {
            addGroup(groupUpdated)
        } else {
            dbQuery {
                Groups.update({ Groups.id_group eq id }) {
                    it[group_title] = groupUpdated.group_title
                    it[group_description] = groupUpdated.group_description
                }
            }
            getGroup(id)!!
        }
    }

    suspend fun addGroup(newGroup: NewGroup): GroupDataClass {
        var key: Int? = 0
        dbQuery {
            key = model.Groups.insert({
                it[group_title] = newGroup.group_title
                it[group_description] = newGroup.group_description
            }) get Groups.id_group
        }
        return getGroup(key!!)!!
    }

    suspend fun deleteGroup(id: Int): Boolean = dbQuery {
        Groups.deleteWhere { Groups.id_group eq id } > 0
    }

    private fun toGroup(row: ResultRow): GroupDataClass =
            GroupDataClass(
                    id_group = row[Groups.id_group],
                    group_title = row[Groups.group_title],
                    group_description = row[Groups.group_description]
            )
}
