package model

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

object TB_User : Table(){
    val id_user = long("id_user").primaryKey().autoIncrement()
    val username = varchar("username", 16)
    val email = varchar("email", 255)
    val password = varchar("password", 32)
    val birth_date = datetime("birth_date")
}

data class User(
        val id_user : Long,
        val username : String,
        val email : String,
        val password : String,
        val birth_date : DateTime
)

data class NewUser(
        val id_user : Long?,
        val username : String,
        val email : String,
        val password : String,
        val birth_date : DateTime
)