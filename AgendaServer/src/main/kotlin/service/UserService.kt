package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class UserService {

    suspend fun getAllUsers(): List<User> = dbQuery {
        TB_User.selectAll().map { toUser(it) }
    }

    suspend fun getUser(id: Long): User? = dbQuery {
        TB_User.select {
            (TB_User.id_user eq id)
        }.mapNotNull { toUser(it) }
                .singleOrNull()
    }

    suspend fun updateUser(updatedUser: NewUser): User {
        val id = updatedUser.id_user
        return if (id == null) {
            addUser(updatedUser)
        } else {
            dbQuery {
                TB_User.update({ TB_User.id_user eq id }) {
                    it[username] = updatedUser.username
                    it[email] = updatedUser.email
                    it[password] = updatedUser.password
                    it[birth_date] = updatedUser.birth_date
                }
            }
            getUser(id)!!
        }
    }

    suspend fun addUser(newUser: NewUser): User {
        var key: Long? = 0
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

    suspend fun deleteUser(id: Long): Boolean = dbQuery {
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