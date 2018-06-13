package service

import model.*
import org.jetbrains.exposed.sql.*
import service.DatabaseFactory.dbQuery

class UserService {

    suspend fun getAllUsers(): List<UserDataClass> = dbQuery {
        User.selectAll().map { toUser(it) }
    }

    suspend fun getUser(id: Int): UserDataClass? = dbQuery {
        User.select {
            (User.id_user eq id)
        }.mapNotNull { toUser(it) }
                .singleOrNull()
    }

    suspend fun updateUser(userUpdated: NewUser): UserDataClass {
        val id = userUpdated.id_user
        return if (id == null) {
            addUser(userUpdated)
        } else {
            dbQuery {
                User.update({ User.id_user eq id }) {
                    it[username] = userUpdated.username
                    it[email] = userUpdated.email
                    it[password] = userUpdated.password
                    it[birth_date] = userUpdated.birth_date
                }
            }
            getUser(id)!!
        }
    }

    suspend fun addUser(newUser: NewUser): UserDataClass {
        var key: Int? = 0
        dbQuery {
            key = User.insert({
                it[username] = newUser.username
                it[email] = newUser.email
                it[password] = newUser.password
                it[birth_date] = newUser.birth_date
            }) get User.id_user
        }
        return getUser(key!!)!!
    }

    suspend fun deleteUser(id: Int): Boolean = dbQuery {
        User.deleteWhere { User.id_user eq id } > 0
    }

    private fun toUser(row: ResultRow): UserDataClass =
            UserDataClass(
                    id_user = row[User.id_user],
                    username = row[User.username],
                    email = row[User.email],
                    password = row[User.password],
                    birth_date = row[User.birth_date]
            )
}