package model

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object User : Table(){
    val id_user = integer("id_user").primaryKey().autoIncrement()
    val username = varchar("username", 16)
    val email = varchar("email", 255)
    val password = varchar("password", 32)
    val birth_date = datetime("birth_date")
}

data class UserDataClass(
        val id_user : Int,
        val username : String,
        val email : String,
        val password : String,
        val birth_date : DateTime
)

data class NewUser(
        val id_user : Int?,
        val username : String,
        val email : String,
        val password : String,
        val birth_date : DateTime
)