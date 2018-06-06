package service

import model.*
import org.jetbrains.exposed.sql.*
import org.joda.time.DateTime
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

    suspend fun updateUser(UserUpdated: NewUser): UserDataClass {
        val id = UserUpdated.id_user
        return if (id == null) {
            addUser(UserUpdated)
        } else {
            dbQuery {
                User.update({ User.id_user eq id }) {
                    it[username] = UserUpdated.username
                    it[email] = UserUpdated.email
                    it[password] = UserUpdated.password
                    it[birth_date] = UserUpdated.birth_date
                }
            }
            getUser(id)!!
        }
    }

    suspend fun addUser(UserAdded: NewUser): UserDataClass {
        var key: Int? = 0
        dbQuery {
            key = User.insert({
                it[username] = UserAdded.username
                it[email] = UserAdded.email
                it[password] = UserAdded.password
                it[birth_date] = UserAdded.birth_date
            }) get User.id_user
        }
        return getUser(key!!)!!
    }

    suspend fun deleteUserTask(id: Int): Boolean = dbQuery {
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