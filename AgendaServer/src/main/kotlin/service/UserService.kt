package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class UserService {

    suspend fun getAllUsers(): List<User> = dbQuery {
        TB_User.selectAll().map { toUser(it) }
    }

    suspend fun getUser(id: Int): User? = dbQuery {
        TB_User.select {
            (TB_User.id_user eq id)
        }.mapNotNull { toUser(it) }
                .singleOrNull()
    }

    suspend fun updateUser(userUpdated: NewUser): User {
        val id = userUpdated.id_user
        return if (id == null) {
            addUser(userUpdated)
        } else {
            dbQuery {
                TB_User.update({ TB_User.id_user eq id }) {
                    it[username] = userUpdated.username
                    it[email] = userUpdated.email
                    it[password] = userUpdated.password
                    it[birth_date] = userUpdated.birth_date
                }
            }
            getUser(id)!!
        }
    }

    suspend fun addUser(newUser: NewUser): User {
        var key: Int? = 0
        dbQuery {
            key = TB_User.insert({
                it[username] = newUser.username
                it[email] = newUser.email
                it[password] = newUser.password
                it[birth_date] = newUser.birth_date
            }) get TB_User.id_user
        }
        return getUser(key!!)!!
    }

    suspend fun deleteUser(id: Int): Boolean = dbQuery {
        TB_User.deleteWhere { TB_User.id_user eq id } > 0
    }

    private fun toUser(row: ResultRow): User =
            User(
                    id_user = row[TB_User.id_user],
                    username = row[TB_User.username],
                    email = row[TB_User.email],
                    password = row[TB_User.password],
                    birth_date = row[TB_User.birth_date]
            )
}